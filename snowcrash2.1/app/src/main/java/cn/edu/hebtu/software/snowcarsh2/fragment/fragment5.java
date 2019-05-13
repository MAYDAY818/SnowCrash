package cn.edu.hebtu.software.snowcarsh2.fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import cn.edu.hebtu.software.snowcarsh2.R;



public class fragment5 extends Fragment {
    private ImageView portrait;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment5,container,false);
        portrait = view.findViewById(R.id.portrait);
        portrait.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(),portrait);
                popupMenu.getMenuInflater().inflate(R.menu.menu_options, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // TODO
                        switch (item.getItemId()){
                            //从相册选图
                            case R.id.item1:
                                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                                intent.setType("image/*");
                                startActivityForResult(intent,1);
                                break;
                            //从相机拍照
                            //case R.id.item2:
                                //Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                                //intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                //startActivityForResult(intentCamera,2);
                                //break;
                            //default:
                                //break;

                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        return view ;
    }

    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                Toast.makeText(getActivity(),data.getData().toString(),Toast.LENGTH_SHORT).show();
                Log.e("MainActivity",data.getData().toString());
                portrait.setImageURI(data.getData());
                break;
            //case 2:
                //Bitmap pic = (Bitmap) data.getExtras().get("data");
                //portrait.setImageBitmap(pic);
                //break;
            //default:
                //break;
        }
    }
}
