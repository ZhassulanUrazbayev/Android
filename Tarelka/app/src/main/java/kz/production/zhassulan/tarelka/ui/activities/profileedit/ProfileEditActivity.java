package kz.production.kuanysh.tarelka.ui.activities.profileedit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import kz.production.kuanysh.tarelka.R;
import kz.production.kuanysh.tarelka.data.network.model.profile.Authorization;
import kz.production.kuanysh.tarelka.ui.activities.mainactivity.MainActivity;
import kz.production.kuanysh.tarelka.ui.base.BaseActivity;
import kz.production.kuanysh.tarelka.ui.welcome.CreateAimActivity;
import kz.production.kuanysh.tarelka.ui.welcome.LoginActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileEditActivity extends BaseActivity implements ProfileEditNewMvpView{

    @Inject
    ProfileEditNewPresenter<ProfileEditNewMvpView> mPresenter;

    @BindView(R.id.profile_edit_photo_new)
    ImageView photo;

    @BindView(R.id.profile_edit_back)
    ImageView back;

    @BindView(R.id.profile_edit_name_new)
    EditText name;

    @BindView(R.id.profile_edit_age_new)
    EditText age;

    @BindView(R.id.profile_edit_weight_new)
    EditText weight;

    @BindView(R.id.profile_edit_aim_new)
    TextView aim;

    @BindView(R.id.profile_edit_aim_new_card)
    CardView profile_edit_aim_new_card;

    @BindView(R.id.profile_edit_add_photo_new)
    TextView addPhoto;

    @BindView(R.id.profile_edit_save_new)
    TextView save;

    @BindView(R.id.profile_edit_height_new)
    EditText height;

    private static Authorization profile;
    private static Uri uriImage;
    private static String filePath;
    private static String imageString;
    private static Bitmap imageMap;
    public static String TAG="edit";
    public static String KEY_EDIT_PROFILE="qwer";
    public static String KEY_EDIT_AIM="aims";
    public static String KEY_EDIT_NAME="aimsn";
    public static String KEY_EDIT_WEIGHT="aimsw";
    public static String KEY_EDIT_AGE="aimsagr";
    public static String KEY_EDIT_HEIGHT="aimshr";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ProfileEditActivity.this);
        setUp();

        if(getIntent().getStringExtra(LoginActivity.KEY_PRO_REG)!=null){
            if(getIntent().getStringExtra(LoginActivity.KEY_PRO_REG).equals(LoginActivity.KEY_PRO_REG)){
                back.setEnabled(false);
                back.setClickable(false);
                back.setVisibility(View.INVISIBLE);
            }
        }


    }

    @Override
    protected void setUp() {
        name.setText(mPresenter.getDataManager().getCurrentUserName().replace("\"",""));
        age.setText(mPresenter.getDataManager().getAge());
        weight.setText(mPresenter.getDataManager().getWeight());
        height.setText(mPresenter.getDataManager().getHeight());
        aim.setText(mPresenter.getDataManager().getAims());

        if(mPresenter.getDataManager().getImage()!=null){
            if(mPresenter.getDataManager().getImage().length()>5){
                Glide.with(this)
                        .load(mPresenter.getDataManager().getImage())
                        .into(photo);
            }else {
                photo.setImageResource(R.mipmap.profile_photo);
            }
        }else {
            photo.setImageResource(R.mipmap.profile_photo);
        }

        if(getIntent().getStringExtra(KEY_EDIT_AIM)!=null){
            if(getIntent().getStringExtra(KEY_EDIT_AIM).equals(KEY_EDIT_AIM)){
                name.setText(getIntent().getStringExtra(KEY_EDIT_NAME).replace("\"",""));
                age.setText(getIntent().getStringExtra(KEY_EDIT_AGE));
                weight.setText(getIntent().getStringExtra(KEY_EDIT_WEIGHT));
                height.setText(getIntent().getStringExtra(KEY_EDIT_HEIGHT));
            }
        }else{
            Log.d("pro", "setUp: null");
        }

    }

    @OnClick(R.id.profile_edit_aim_new_card)
    public void openEdit(){
        mPresenter.onEditCardClick();
    }

    @OnClick(R.id.profile_edit_back)
    public void setGo(){
        mPresenter.onBackClick();
    }

    @OnClick(R.id.profile_edit_save_new)
    public void save() {
        if(filePath!=null && uriImage!=null){
            Log.i(TAG, "save:wit image");
            //FileUtils.getFile(uriImage);
            File file = new File(filePath);
            try {
                File compressedImageFile = new Compressor(this).compressToFile(file);
                RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), compressedImageFile);
                //RequestBody filePart= RequestBody.create(MediaType.parse(getContentResolver().getType(uriImage)), file);

                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("image", compressedImageFile.getName(), fbody);

                mPresenter.onSaveClick(body,
                        name.getText().toString(),
                        age.getText().toString(),
                        weight.getText().toString(),
                        height.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*String descriptionString = "image/*";
            RequestBody description =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, descriptionString);*/

        }
        else{
            Log.d(TAG, "save: onSaveClickWithoutImage ");
            mPresenter.onSaveClickWithoutImage(name.getText().toString(),
                    age.getText().toString(),
                    weight.getText().toString(),
                    height.getText().toString());
        }



    }

    @OnClick(R.id.profile_edit_add_photo_new)
    public void addPhoto(View view){
        mPresenter.onAddPhotoClick();
    }



    @Override
    public void openProfileFragment() {
        Intent intent=new Intent(ProfileEditActivity.this, MainActivity.class);
        intent.putExtra(KEY_EDIT_PROFILE,KEY_EDIT_PROFILE);
        startActivity(intent);
    }


    @Override
    public void openImagePicker() {
        com.esafirm.imagepicker.features.ImagePicker.create(this)
                // set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
                .toolbarFolderTitle("Folder") // folder selection title
                .toolbarImageTitle("Нажмите чтобы выбрать") // image selection title
                .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                .includeVideo(true) // Show video on image picker
                .single()
                .limit(1) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Tarelka") // directory name for captured image  ("Camera" folder by default)
                // .theme(R.style.CustomImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
                .enableLog(false) // disabling log
                //.imageLoader(new GrayscaleImageLoder()) // custom image loader, must be serializeable
                .start(); // start image picker activity with request code
    }

    @Override
    public void openAimsEditActivity() {
        Intent intent=new Intent(ProfileEditActivity.this, CreateAimActivity.class);
        intent.putExtra(KEY_EDIT_AIM,KEY_EDIT_AIM);
        intent.putExtra(KEY_EDIT_NAME,name.getText().toString());
        intent.putExtra(KEY_EDIT_WEIGHT,weight.getText().toString());
        intent.putExtra(KEY_EDIT_HEIGHT,height.getText().toString());
        intent.putExtra(KEY_EDIT_AGE,age.getText().toString());
        startActivity(intent);
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (com.esafirm.imagepicker.features.ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            File file = new File(image.getPath());

            uriImage = Uri.fromFile(file);
            filePath=image.getPath();

            Glide.with(this).load(filePath).into(photo);
            Log.d(TAG, "onActivityResult: ");

        }
    }
    public static String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
