package com.example.appbook;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class MainDocSachActivity extends Activity {
    private WebView webView;
    ImageView back_icon;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docsach);

        // Khởi tạo WebView
        webView = findViewById(R.id.webview);
        webView = findViewById(R.id.webview);
        back_icon = findViewById(R.id.back_icon);

        // Cấu hình WebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        // Đặt WebViewClient để xử lý trong ứng dụng
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.e("WebViewError", "Error loading page: " + error.getDescription());
                // Hiển thị thông báo lỗi cho người dùng (nếu cần)
            }
        });

//        // Đặt WebViewClient để tránh mở liên kết trong trình duyệt ngoài
//        webView.setWebViewClient(new WebViewClient());
//
//        // Đặt WebChromeClient (Optional) để hiển thị tiến trình tải trang
//        webView.setWebChromeClient(new WebChromeClient());


        // Nhận dữ liệu từ Intent
        String truyen_tentruyen = getIntent().getStringExtra("truyen_tentruyen");
        String truyen_tentacgia = getIntent().getStringExtra("truyen_tentacgia");
        String truyen_image = getIntent().getStringExtra("truyen_image");
        String theloai_ten = getIntent().getStringExtra("theloai_ten");
        String truyen_mota = getIntent().getStringExtra("truyen_mota");
        String truyen_link = getIntent().getStringExtra("truyen_link");
        int truyen_id = getIntent().getIntExtra("truyen_id", -1); // Giá trị mặc định là -1
        int theloai_id = getIntent().getIntExtra("theloai_id", -1);

        Log.d("truyen", "truyen_id: " + truyen_id);
        Log.d("truyen", "truyen_tentacgia: " + truyen_tentacgia);
        Log.d("truyen", "truyen_image: " + truyen_image);
        Log.d("truyen", "theloai_ten: " + theloai_ten);
        Log.d("truyen", "truyen_mota: " + truyen_mota);
        Log.d("truyen", "truyen_link: " + truyen_link);
        Log.d("truyen", "truyen_tentruyen: " + truyen_tentruyen);
        Log.d("truyen", "theloai_id: " + theloai_id);

        // Tải URL vào WebView nếu hợp lệ
        if (truyen_link != null && !truyen_link.trim().isEmpty() && truyen_link.startsWith("http")) {
            webView.clearCache(true); // Xóa cache trước khi tải
            webView.loadUrl(truyen_link);
        } else {
            Log.e("WebViewError", "Invalid URL: " + truyen_link);
        }


        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()) {
                    webView.goBack(); // Quay lại trang trước trong WebView
                } else {
                    finish(); // Kết thúc Activity hiện tại để quay lại Activity trước đó
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack(); // Quay lại trang trước
        } else {
            super.onBackPressed(); // Thoát ứng dụng
        }
    }

}
