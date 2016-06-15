package com.example.administrator.news.ui;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.news.R;

/**
 * A simple {@link Fragment} subclass.
 * 左边侧拉界面
 */
public class FragmentMenu extends Fragment {
    private RelativeLayout[] rls = new RelativeLayout[5];
    private FragmentMenu mMenu;

    public FragmentMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_left, container, false);

        rls[0] = (RelativeLayout) view.findViewById(R.id.rl_news);
        rls[1] = (RelativeLayout) view.findViewById(R.id.rl_reading);
        rls[2] = (RelativeLayout) view.findViewById(R.id.rl_local);
        rls[3] = (RelativeLayout) view.findViewById(R.id.rl_comment);
        rls[4] = (RelativeLayout) view.findViewById(R.id.rl_photo);
        for (int i = 0; i < rls.length; i++) {
            rls[i].setOnClickListener(onClickListener);
        }
        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < rls.length; i++) {
                rls[i].setBackgroundColor(0);
            }
            switch (v.getId()) {
                case R.id.rl_news:
                    rls[0].setBackgroundColor(0x33c85555);
                    Intent intent=new Intent(getActivity(),HomeActivity.class);
                    startActivity(intent);
                    mMenu.onDestroy();
                    break;
                case R.id.rl_reading:
                    rls[0].setBackgroundColor(0x33c85555);
//                    Intent intent1=new Intent(getActivity(),FragmentFavorite.class);
//                    startActivity(intent1);
//                    mMenu.onDestroy();
                    ((HomeActivity) getActivity()).showFragmentFavorite();
                    break;
                case R.id.rl_local:
                    rls[0].setBackgroundColor(0x33c85555);

                    break;
                case R.id.rl_comment:
                    rls[0].setBackgroundColor(0x33c85555);
                    Intent intent1=new Intent(getActivity(),CommentActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.rl_photo:
                    rls[0].setBackgroundColor(0x33c85555);

                    break;
            }
        }
    };
}
