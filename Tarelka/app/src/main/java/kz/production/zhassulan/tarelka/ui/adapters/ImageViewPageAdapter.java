package kz.production.kuanysh.tarelka.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import kz.production.kuanysh.tarelka.R;

/**
 * Created by User on 21.07.2018.
 */

public class ImageViewPageAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<String> imageLink;

    public ImageViewPageAdapter(Context mContext, List<String> imageLink) {
        this.mContext = mContext;

        this.imageLink = imageLink;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return position+"/"+imageLink.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d("myTag", "instantiateItem: "+imageLink.get(position).toString());

        mLayoutInflater=((Activity) mContext).getLayoutInflater();
        //mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mLayoutInflater.inflate(R.layout.viewpager_row_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_viewpager);
        Glide.with(mContext).load(imageLink.get(position).toString()).into(imageView);


        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public int getCount() {
        return imageLink.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((LinearLayout) object);
    }
}
