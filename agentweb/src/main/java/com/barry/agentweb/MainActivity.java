package com.barry.agentweb;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        // For Android < 3.0
        @Override
        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            super.openFileChooser(valueCallback);
        }

        //For Android 3.0 - 4.0
        @Override
        public void openFileChooser(ValueCallback valueCallback, String acceptType) {
            super.openFileChooser(valueCallback, acceptType);
        }

        // For Android 4.0 - 5.0
        @Override
        public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String capture) {
            super.openFileChooser(uploadFile, acceptType, capture);
        }

        // For Android > 5.0
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            Log.i("Barry", "fileChooserParams:" + fileChooserParams.getAcceptTypes() + "  getTitle:" + fileChooserParams.getTitle() + " accept:" + Arrays.toString(fileChooserParams.getAcceptTypes()) + " length:" + fileChooserParams.getAcceptTypes().length + "  isCaptureEnabled:" + fileChooserParams.isCaptureEnabled() + "  " + fileChooserParams.getFilenameHint() + "  intent:" + fileChooserParams.createIntent().toString() + "   mode:" + fileChooserParams.getMode());
            boolean isChooseImage = true;
            String acceptTypes[] = fileChooserParams.getAcceptTypes();
            for (int i = 0; i < acceptTypes.length; i++) {
                if (!acceptTypes[i].contains("image")) {
                    isChooseImage = false;
                    break;
                }
            }
            if (isChooseImage) {
                selectImages(filePathCallback);
                return true;
            } else {
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout container = findViewById(R.id.container);
        AgentWeb mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb()
                .ready()
                .go("file:///android_asset/upload.html");
    }

    private void selectImages(ValueCallback<Uri[]> filePathCallback) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .compress(false)// 是否压缩 true or false
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cutOutQuality(90)
                .minimumCompressSize(500)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .enableCrop(false)
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        List<Uri> uris = new ArrayList<>();
                        ListIterator<LocalMedia> listIterator = result.listIterator();
                        while (listIterator.hasNext()) {
                            LocalMedia localMedia = listIterator.next();
                            String path = null;
                            if (localMedia.getCutPath() != null) {
                                path = localMedia.getCutPath();
                            } else {
                                path = localMedia.getPath();
                            }
                            File file = new File(path);
                            if (file.exists()) {
                                uris.add(Uri.fromFile(file));
                            }
                        }
                        filePathCallback.onReceiveValue(uris.toArray(new Uri[uris.size()]));
                    }

                    @Override
                    public void onCancel() {
                        filePathCallback.onReceiveValue(null);
                    }
                });
    }

}
