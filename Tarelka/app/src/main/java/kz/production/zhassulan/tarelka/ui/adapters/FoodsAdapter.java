package kz.production.kuanysh.tarelka.ui.adapters;

/**
 * Created by User on 03.07.2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import kz.production.kuanysh.tarelka.R;
import kz.production.kuanysh.tarelka.data.network.model.aim.Result;
import kz.production.kuanysh.tarelka.utils.AppConst;

public class FoodsAdapter extends BaseAdapter {

    private  Context mContext;
    private List<Result> aimsList;
    private List<Result> newList;
    private HashSet<Integer> list;

    public FoodsAdapter() {
    }

    @Override
    public int getCount() {
        return aimsList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.foods_grid_item, null);

        final TextView name=(TextView)convertView.findViewById(R.id.foods_name);
        name.setPadding(0,20,0,0);

        name.setText(aimsList.get(position).getTitle());
        final ImageView image=(ImageView)convertView.findViewById(R.id.foods_grid_item_image);
        final ImageView select=(ImageView)convertView.findViewById(R.id.foods_select);

        Glide.with(image.getContext())
                .load(aimsList.get(position).getImage())
                .into(image);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select.getVisibility()==View.GONE){
                    list.add(aimsList.get(position).getId());
                    newList.add(aimsList.get(position));
                    setAnimation(name,select, AppConst.TAG_GONE);
                    /*for (int i = 0; i <aimsList.size() ; i++) {
                        View view=parent.getChildAt(i);
                        TextView name= (TextView) view.findViewById(R.id.aims_name);
                        ImageView select= (ImageView) view.findViewById(R.id.aims_select);
                        if(i!=position){
                            setAnimation(name,select, AppConst.TAG_VISIBLE);
                        }else{
                            setAnimation(name,select, AppConst.TAG_GONE);
                        }

                    }*/
                }else if(select.getVisibility()==View.VISIBLE){
                    list.remove(aimsList.get(position).getId());
                    newList.remove(aimsList.get(position));
                    setAnimation(name,select, AppConst.TAG_VISIBLE);

                }
            }
        });

        if(list.contains(aimsList.get(position).getId())){
            setAnimation(name,select, AppConst.TAG_GONE);
        }else{
            setAnimation(name,select, AppConst.TAG_VISIBLE);
        }


        return convertView;
    }
    private class ViewHolder {
        private TextView name;
        private ImageView select;

        public ViewHolder(TextView name,ImageView select) {
            this.name = name;
            this.select = select;
        }
    }
    private void setAnimation(TextView name,ImageView select, String status){


        if(status.equals(AppConst.TAG_GONE)){
            Animation animate = new AlphaAnimation(0f, 1.0f);
            animate.setDuration(600);
            animate.setFillAfter(true);
            name.startAnimation(animate);
            name.setPadding(0,0,0,0);
            select.startAnimation(animate);
            select.setVisibility(View.VISIBLE);

        }else if(status.equals(AppConst.TAG_VISIBLE)){
            Animation animate = new AlphaAnimation(0f, 1.0f);
            animate.setDuration(600);
            animate.setFillAfter(true);
            name.startAnimation(animate);
            name.setPadding(0,15,0,0);

            Animation animate1 = new AlphaAnimation(1f, 0f);
            animate.setDuration(600);
            animate.setFillAfter(true);
            select.startAnimation(animate1);
            select.setVisibility(View.GONE);
        }


        //return animate;
    }
    public void addItems(List<Result> aims){
        aimsList=aims;
        notifyDataSetChanged();
    }
    public void addNextItems(List<Result> newkng){
        newList=newkng;
    }
    public void addSelectableList(HashSet<Integer> mnewList){
        list=mnewList;
    }
    public void addContext(Context context){
        mContext=context;
    }


}