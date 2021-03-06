package com.miniandroid.myzzung.supoint.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.miniandroid.myzzung.supoint.BaseFragment;
import com.miniandroid.myzzung.supoint.CoilApplication;
import com.miniandroid.myzzung.supoint.R;
import com.miniandroid.myzzung.supoint.volley.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private CoilApplication app;
    private TextView home_text = null;

    private final String server_url = "http://ljs93kr.cafe24.com/coil/test_dir/coil_test.php";

    private FloatingActionMenu menuRed;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;

    private ImageView img_avatar;

    private Button home_btn;

    private int level_status = 1;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void init(View rootView) {
        menuRed = (FloatingActionMenu) rootView.findViewById(R.id.menu_red);

        fab1 = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) rootView.findViewById(R.id.fab3);

        img_avatar = (ImageView)rootView.findViewById(R.id.img_home_avata);

        home_text = (TextView) rootView.findViewById(R.id.home_text);

        home_btn = (Button) rootView.findViewById(R.id.home_button);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        app = (CoilApplication) getActivity().getApplicationContext();
        final RequestQueue queue = MyVolley.getInstance(getActivity()).getRequestQueue();

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        init(rootView);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);

        // 간단한 이름
        home_text.setText(app.user_id);

        // 간단한 버튼
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST,
//                        server_url,
//                        new JSONObject(),
//                        networkSuccessListener(),
//                        networkErrorListener());
//
//                queue.add(myReq);
                showProgress(2);
                switch (level_status){
                    case 1:
                        img_avatar.setBackgroundResource(R.drawable.img_avatar_lev2);
                        level_status+=1;
                        break;
                    case 2:
                        img_avatar.setBackgroundResource(R.drawable.img_avatar_lev3);
                        level_status+=1;
                        break;
                    case 3:
                        img_avatar.setBackgroundResource(R.drawable.img_avatar_lev4);
                        level_status+=1;
                        break;
                    case 4:
                        img_avatar.setBackgroundResource(R.drawable.img_avatar_lev1);
                        level_status = 1;
                        break;
                    default:
                        Toast.makeText(getActivity(), "What The !", Toast.LENGTH_SHORT).show();
                }
                home_text.setText("아바타의 레벨이 "+level_status+"가 되었습니다");

            }
        });
//        new MaterialDialog.Builder(getActivity())
//                .title("Input")
//                .content("Contents")
//                .inputType(InputType.TYPE_CLASS_NUMBER)
//                .input("hint",null, new MaterialDialog.InputCallback() {
//                    @Override
//                    public void onInput(MaterialDialog dialog, CharSequence input) {
//                        // Do something
//                    }
//                }).show();

        return rootView;
    }
    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String from_server = null;
                try {
                    from_server = response.getString("test");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                home_text.setText(from_server);

            }
        };
    }
    private Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fab1:
                    Toast.makeText(getActivity(), "fab1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fab2:
                    Toast.makeText(getActivity(), "fab2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fab3:
                    Toast.makeText(getActivity(), "fab3", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


}
