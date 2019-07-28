package com.ngocdai.tikitest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private String[] list;
    int[][] color1;
    public MainAdapter(String[] list) {
        this.list = list;
        color1 = new int[list.length][3];
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_text, viewGroup, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainHolder mainHolder, int i) {
        String[] spaceStr = list[i].split("\\s+");

        if (spaceStr.length > 1){
            String[] resultOk = FindSpaceNear(list[i].trim());
            mainHolder.textView.setText(resultOk[0] + "\n" + resultOk[1]);
        }else {
            mainHolder.textView.setText(list[i]);
        }
        Toast.makeText(mainHolder.itemView.getContext(), "" + color1[i][1], Toast.LENGTH_SHORT).show();
        if (color1[i][0] == 0 && color1[i][1] == 0 && color1[i][2] == 0){
            color1[i] = RandomColor();

            Drawable drawable = mainHolder.itemView.getResources().getDrawable(R.drawable.shape_text);
            drawable.setColorFilter(Color.rgb(color1[i][0], color1[i][1], color1[i][2]), PorterDuff.Mode.SRC_ATOP);
            mainHolder.textView.setBackgroundDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public  static class MainHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MainHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewText);
        }
    }

    private String[] FindSpaceNear(String str){
        String result1 = "", result2 = "";
        int leng = str.length();
        //middle length of tring
        int leng2 = Math.round((float)leng/2);
        String str1 = str.substring(0, leng2);
        String str2 = str.substring(leng2, leng);
        String[] strSplit1 = str1.split("\\s+");
        String[] strSplit11 = str1.split("\\s+", strSplit1.length + 2);
        String[] strSplit2 = str2.split("\\s+");
        String[] strSplit21 = str2.split("\\s+", strSplit2.length + 2);
        if (strSplit11[strSplit11.length -1].length() <= strSplit21[0].length()){
            for (int i = 0; i < strSplit11.length - 1; i++){
                result1 += strSplit11[i] + " ";
            }
            strSplit21[0] = strSplit11[strSplit11.length - 1] + strSplit21[0];
            for (int j = 0; j < strSplit21.length; j++){
                result2 += strSplit21[j] + " ";
            }
        }else {
            strSplit11[strSplit11.length -1] = strSplit11[strSplit11.length - 1] + strSplit21[0];
            for (int i = 0; i < strSplit11.length; i++){
                result1 += strSplit11[i] + " ";
            }

            for (int j = 1; j < strSplit21.length; j++){
                result2 += strSplit21[j] + " ";
            }
        }

        return new String[]{result1, result2};
    }

    private int[] RandomColor(){
        int r = 0, g = 0, b = 0, result = 650;
        while (result >= 650 || r + g + b < 255){
            r = new Random().nextInt(256);
            g = new Random().nextInt(256);
            b = new Random().nextInt(256);
            result = r + g + b;
        }
        return new int[]{r, g, b};
    }
}
