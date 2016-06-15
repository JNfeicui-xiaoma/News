package com.example.administrator.news.ui;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.news.R;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuRight extends Fragment {
    public static final String TAG="FragmentMenuRight";
    private View view;
    private RelativeLayout relativeLayout_unlogin;
    private RelativeLayout relativeLayout_logined;
    private boolean islogin;
    private SharedPreferences sharedPreferences;
    private ImageView imageView1;
    private TextView textView1, updataTv;

    //分享到微信
    private ImageView iv_friend,iv_friends;

    /**
     * 分享位置规定
     */
    private IWXAPI api;
    public static final String App_ID="wx4394a3a9b4509be0";
    public FragmentMenuRight() {
        // Required empty public constructor
    }

//    public static final int WEBCHAT = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu_right, container, false);
        sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        islogin = sharedPreferences.getBoolean("islogin", false);
        relativeLayout_unlogin = (RelativeLayout) view.findViewById(R.id.relativelayout_unlogin);
        relativeLayout_logined = (RelativeLayout) view.findViewById(R.id.relativelayout_logined);
        imageView1 = (ImageView) view.findViewById(R.id.imageView1_right);
        updataTv = (TextView) view.findViewById(R.id.updata_version);
        textView1 = (TextView) view.findViewById(R.id.textView1_right);

        api= WXAPIFactory.createWXAPI(getActivity(),App_ID);
        api.registerApp(App_ID);
        iv_friend= (ImageView) view.findViewById(R.id.fun_friend);
        iv_friend.setOnClickListener(mOnClick);
        iv_friends= (ImageView) view.findViewById(R.id.fun_friends);
        iv_friends.setOnClickListener(mOnClick);

        textView1.setOnClickListener(mOnClick);
        imageView1.setOnClickListener(mOnClick);
        return view;
    }

    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 判断登陆
            if (v.getId() == R.id.imageView1_right || v.getId() == R.id.textView1_right) {
                ((HomeActivity) getActivity()).showDengluFragment();
            }
            switch (v.getId()){
                case R.id.fun_friend:
//                    api.openWXApp();
                    //动态创建EditText，用于输入文本
                    final EditText editor = new EditText(getActivity());
                    //设置布局
                    editor.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                    // 设置默认的分享文本
                    editor.setText("请输入要分享的文字！");
                    // 创建dialog对象
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setTitle("共享文本");
                    //将EditText与对话框绑定
                    builder.setView(editor);
                    builder.setMessage("请输入要分享的文本");
                    builder.setPositiveButton("分享", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //获取待分享文本
                            String text = editor.getText().toString();
                            if (text == null || text.length() == 0) {
                                return;
                            }

                            //第一步：创建一个用于封装待分享文本的WXTextObject对象
                            WXTextObject textObj = new WXTextObject();
                            textObj.text = text;

                            //第二步： 创建WXMediaMessage对象，该对象用于Android客户端向微信发送数据
                            WXMediaMessage msg = new WXMediaMessage();
                            msg.mediaObject = textObj;
                            msg.description = text;//设置一个描述

                            //第三步： 创建一个请求微信客户端的SendMessageToWX.Req对象
                            SendMessageToWX.Req req = new SendMessageToWX.Req();
                            req.message = msg;
                            //设置唯一的标识
                            req.transaction = buildTransaction("Text");
                            //表示发送给朋友
                            req.scene = SendMessageToWX.Req.WXSceneSession;

                            // 第四步：发送给微信客户端
                            Toast.makeText(getActivity(), String.valueOf
                                    (api.sendReq(req)), Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    final AlertDialog alert = builder.create();
                    alert.show();

//                   Toast.makeText(getActivity(),String.valueOf(api.openWXApp()), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fun_friends:
                    //                    api.openWXApp();
                    //动态创建EditText，用于输入文本
                    final EditText editor1 = new EditText(getActivity());
                    //设置布局
                    editor1.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                    // 设置默认的分享文本
                    editor1.setText("请输入要分享的文字！");
                    // 创建dialog对象
                    final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setIcon(android.R.drawable.ic_dialog_alert);
                    builder1.setTitle("共享文本");
                    //将EditText与对话框绑定
                    builder1.setView(editor1);
                    builder1.setMessage("请输入要分享的文本");
                    builder1.setPositiveButton("分享", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //获取待分享文本
                            String text = editor1.getText().toString();
                            if (text == null || text.length() == 0) {
                                return;
                            }

                            //第一步：创建一个用于封装待分享文本的WXTextObject对象
                            WXTextObject textObj = new WXTextObject();
                            textObj.text = text;

                            //第二步： 创建WXMediaMessage对象，该对象用于Android客户端向微信发送数据
                            WXMediaMessage msg = new WXMediaMessage();
                            msg.mediaObject = textObj;
                            msg.description = text;//设置一个描述

                            //第三步： 创建一个请求微信客户端的SendMessageToWX.Req对象
                            SendMessageToWX.Req req = new SendMessageToWX.Req();
                            req.message = msg;
                            //设置唯一的标识
                            req.transaction = buildTransaction("Text");
                            //表示发送给朋友
                            req.scene = SendMessageToWX.Req.WXSceneTimeline;

                            // 第四步：发送给微信客户端
                            Toast.makeText(getActivity(), String.valueOf
                                    (api.sendReq(req)), Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder1.setNegativeButton("取消", null);
                    final AlertDialog alert1 = builder1.create();
                    alert1.show();
                    break;
            }
        }
    };
    private String buildTransaction(final String type){
        return (type==null)?String.valueOf(System.currentTimeMillis())
                :type+System.currentTimeMillis();
    }

}

