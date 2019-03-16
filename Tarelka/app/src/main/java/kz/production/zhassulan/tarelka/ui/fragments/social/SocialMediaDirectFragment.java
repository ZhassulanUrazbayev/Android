package kz.production.kuanysh.tarelka.ui.fragments.social;


import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URLEncoder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.production.kuanysh.tarelka.R;
import kz.production.kuanysh.tarelka.di.component.ActivityComponent;
import kz.production.kuanysh.tarelka.ui.base.BaseFragment;
import kz.production.kuanysh.tarelka.ui.fragments.ChatFragment;
import kz.production.kuanysh.tarelka.ui.fragments.ProfileFragment;

import static kz.production.kuanysh.tarelka.ui.activities.mainactivity.MainActivity.TAG_CHAT;
import static kz.production.kuanysh.tarelka.ui.activities.mainactivity.MainActivity.TAG_PROFILE;
import static kz.production.kuanysh.tarelka.ui.fragments.ChatFragment.CHAT_SOCIAL_KEY;
import static kz.production.kuanysh.tarelka.ui.fragments.ProfileFragment.PROFILE_SOCIAL_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialMediaDirectFragment extends BaseFragment implements SocialMvpView {


    @Inject
    SocialPresenter<SocialMvpView> mPresenter;

    @BindView(R.id.social_back)
    ImageView back;

    @BindView(R.id.via_whatsapp)
    TextView whatsapp;

    @BindView(R.id.via_telegram)
    TextView telegram;

    @BindView(R.id.via_vk)
    TextView vk;

    @BindView(R.id.via_facebook)
    TextView facebook;

    @BindView(R.id.via_viber)
    TextView viber;

    private static String MESSAGE;
    private static String WHATSAPP_PHONE;
    private static String TELEGRAM_ID;
    private static String VK_CHATID;
    private static String VIBER_ID;
    private static String FACEBOOK_ID;
    private static String ACTION_BACK;

    public SocialMediaDirectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_social_media_direct, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        final Bundle bundle=getArguments();
        if(bundle!=null){
            if(bundle.getString(PROFILE_SOCIAL_KEY).equals(PROFILE_SOCIAL_KEY)){
                ACTION_BACK=PROFILE_SOCIAL_KEY;
            }else if(bundle.getString(PROFILE_SOCIAL_KEY).equals(CHAT_SOCIAL_KEY)){
                ACTION_BACK=CHAT_SOCIAL_KEY;
            }
        }
        int count = getFragmentManager().getBackStackEntryCount();
        Log.d("back", "onBackPressed:Social count "+count);


        return view;
    }

    @OnClick(R.id.via_whatsapp)
    public void goWhats(){
        mPresenter.onWhatsappClick();
    }
    @OnClick(R.id.via_telegram)
    public void goTelegram(){
        mPresenter.onTelegramClick();
    }
    @OnClick(R.id.via_facebook)
    public void goFacebook(){
        mPresenter.onFacebookClick();
    }
    @OnClick(R.id.via_vk)
    public void goVK(){
        mPresenter.onVkClick();
    }
    @OnClick(R.id.via_viber)
    public void goViber(){
        mPresenter.onViberClick();
    }

    @OnClick(R.id.social_back)
    public void goBackSocial(){
        mPresenter.onBackClick();
    }

    @Override
    protected void setUp(View view) {
        mPresenter.onViewPrepared();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void getAdminInfo(String whats,String telegram,String facebook,String viber,String vk) {
        WHATSAPP_PHONE=whats;
        TELEGRAM_ID=telegram;
        FACEBOOK_ID=facebook;
        VIBER_ID=viber;
        VK_CHATID=vk;
    }


    @Override
    public void openWhatsapp() {
        if(WHATSAPP_PHONE!=null){
            if(isAppAvailable(getActivity(),"com.whatsapp")==true){
                String smsNumber = WHATSAPP_PHONE.replace("+","").replace(" ",""); // E164 format without '+' sign
                PackageManager packageManager = getActivity().getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);

                try {
                    String url = "https://api.whatsapp.com/send?phone="+ smsNumber +"&text=" + URLEncoder.encode("", "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        getActivity().startActivity(i);
                    }
                } catch (Exception e){
                    Log.d("myTag", "openWhatsapp: "+e.getMessage());
                    e.printStackTrace();
                }
            }else {
                mPresenter.getMvpView().onError("Приложение не установлено!");
            }
        }else {
            mPresenter.getMvpView().onError("Что-то пошло не так!");
        }


    }

    @Override
    public void openTelegram() {
        if(TELEGRAM_ID!=null){
            if(isAppAvailable(getActivity(),"org.telegram.messenger")==true){
                Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/"+TELEGRAM_ID));
                //telegram.setType("text/plain");
                telegram.putExtra(Intent.EXTRA_TEXT, "");
                telegram.setPackage("org.telegram.messenger");
                getActivity().startActivity(telegram);
            }else {
                mPresenter.getMvpView().onError("Приложение не установлено!");
            }
        }else {
            mPresenter.getMvpView().onError("Что-то пошло не так!");
        }


    }

    @Override
    public void openVK() {
        if(VK_CHATID!=null){
            if(isAppAvailable(getActivity(),"com.vkontakte.android")==true){
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("http://vk.com/im?sel="+VK_CHATID)));
                //intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                getActivity().startActivity(intent);
            }else {
                mPresenter.getMvpView().onError("Приложение не установлено!");
            }
        }else {
            mPresenter.getMvpView().onError("Что-то пошло не так!");
        }


    }

    @Override
    public void openFacebook() {
       // mPresenter.getMvpView().showMessage(FACEBOOK_ID);
        if(FACEBOOK_ID!=null){
            if(isAppAvailable(getActivity(),"com.facebook.orca")==true){

                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("fb-messenger://user-thread/"+String.valueOf(FACEBOOK_ID)));
                startActivity(intent);
            }else {
                mPresenter.getMvpView().onError("Приложение не установлено!");
            }
        }else {
            mPresenter.getMvpView().onError("Что-то пошло не так!");
        }

    }

    @Override
    public void openViber() {
        if(VIBER_ID!=null){
            if(isAppAvailable(getActivity(),"com.viber.voip")==true){
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("viber://pa?chatURI="+String.valueOf(VIBER_ID)));
                startActivity(smsIntent);
            }else {
                mPresenter.getMvpView().onError("Приложение не установлено!");
            }
        }else {
            mPresenter.getMvpView().onError("Что-то пошло не так!");
        }



    }
    public static boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    @Override
    public void openBack() {
        if(ACTION_BACK==CHAT_SOCIAL_KEY){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, ChatFragment.newInstance(), TAG_CHAT)
                    .commit();
            getActivity().getSupportFragmentManager().popBackStack();

        }else if(ACTION_BACK==PROFILE_SOCIAL_KEY){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, ProfileFragment.newInstance(), TAG_PROFILE)
                    .commit();
            getActivity().getSupportFragmentManager().popBackStack();

        }
    }
}
