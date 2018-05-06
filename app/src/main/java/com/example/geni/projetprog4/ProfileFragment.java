package com.example.geni.projetprog4;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment
{
    //Le fragment du profile lorsqu'on choisi profile dans le drawer
    View vue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        vue = inflater.inflate(R.layout.profile_layout, container, false);
        if(this.getArguments() != null)
        {
            byte[] avatar = this.getArguments().getByteArray("avatar");
            Bitmap bmp = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
            ((ImageView)vue.findViewById(R.id.imgAvatarProfile)).setImageBitmap(bmp);
            ((TextView)vue.findViewById(R.id.txtIdentifiantProfile)).setText(this.getArguments().getString("username"));
            ((TextView)vue.findViewById(R.id.txtCourrielProfile)).setText(this.getArguments().getString("email"));
            ((TextView)vue.findViewById(R.id.txtPointage)).setText(String.valueOf(this.getArguments().getInt("points")));
        }
        return vue;
    }
}
