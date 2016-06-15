package com.example.administrator.news.ui;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.news.R;
import com.example.administrator.news.utils.Constants;
import com.example.administrator.news.utils.SPUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class DengluFragment extends Fragment {

    private View mView;
    private EditText et_title, et_pwd;
    private Button but_zhuce, but_denglu;
    private DengluFragment mFragment;

    public DengluFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_denglu, container, false);
        et_title = (EditText) mView.findViewById(R.id.et_title);
        et_pwd = (EditText) mView.findViewById(R.id.et_pwd);
        but_zhuce = (Button) mView.findViewById(R.id.zhuce);
        but_denglu = (Button) mView.findViewById(R.id.denglu);

        but_denglu.setOnClickListener(clickListener);
        but_zhuce.setOnClickListener(clickListener);

        return mView;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.zhuce:
                    showSetupDialog();
                    break;
                case R.id.denglu:
                    showEnterDialog();
                    Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void showEnterDialog() {
        //验证内容
        String name = et_title.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String savename = SPUtils.getString(getActivity(), Constants.USERNAME);
        String savepwd = SPUtils.getString(getActivity(), Constants.USERPWD);
        if (!name.equals(savename)) {
            Toast.makeText(getActivity(), "用户名不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(savepwd)) {
            Toast.makeText(getActivity(), "密码不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        //保存edittext里的内容
        SPUtils.putString(getActivity(), Constants.USERNAME, name);
        SPUtils.putString(getActivity(), Constants.USERPWD, pwd);
        Intent intent = new Intent(getActivity(), USERActivity.class);
        startActivity(intent);
        mFragment.onDestroy();
    }

    private void showSetupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mView = View.inflate(getActivity(), R.layout.item_login, null);
        //注册按钮
        final EditText etname = (EditText) mView.findViewById(R.id.et_item_name);
        final EditText etpwd = (EditText) mView.findViewById(R.id.et_pwd1);
        //再次输入密码
        final EditText etconfirmPwd = (EditText) mView.findViewById(R.id.et_pwd2);

        //      确认或取消
        Button btn_cancle = (Button) mView.findViewById(R.id.quxiao);
        Button btn_submit = (Button) mView.findViewById(R.id.zhuce);
        builder.setView(mView);
        final AlertDialog dialog = builder.show();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验文本框内容
                String name = etname.getText().toString().trim();
                String pwd = etpwd.getText().toString().trim();
                String confirmPwd = etconfirmPwd.getText().toString().trim();
                // 是否为空
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd)) {
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //    是否相同
                if (!pwd.equals(confirmPwd)) {
                    Toast.makeText(getActivity(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                String savename = SPUtils.getString(getActivity(), Constants.USERNAME);
                if (name.equals(savename)) {
                    Toast.makeText(getActivity(), "用户名已存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                SPUtils.putString(getActivity(), Constants.USERNAME, name);
                SPUtils.putString(getActivity(), Constants.USERPWD, pwd);
                dialog.dismiss();
                Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_LONG).show();
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
