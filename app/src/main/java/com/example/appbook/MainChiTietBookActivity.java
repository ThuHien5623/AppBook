package com.example.appbook;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainChiTietBookActivity extends Activity {
    MyDatabaseHelper databasedoctruyen;
    ImageView back_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtin);

        back_button = findViewById(R.id.back_button);

        // Nhận dữ liệu từ Intent
//        String tentheloai = getIntent().getStringExtra("theloai_ten");
        String truyen_tentruyen = getIntent().getStringExtra("truyen_tentruyen");
        String truyen_tentacgia = getIntent().getStringExtra("truyen_tentacgia");
        String truyen_image = getIntent().getStringExtra("truyen_image");
        String theloai_ten = getIntent().getStringExtra("theloai_ten");
        String truyen_mota = getIntent().getStringExtra("truyen_mota");

        // Hiển thị tên truyện
        TextView book_title = findViewById(R.id.book_title);
        book_title.setText(truyen_tentruyen);

        // Hiển thị tên thể loại
        TextView book_genre = findViewById(R.id.book_genre);
        book_genre.setText("Thể loại: " + theloai_ten);

        // Hiển thị tên tác giả
        TextView book_author = findViewById(R.id.book_author);
        book_author.setText("Tác giả: " + truyen_tentacgia);

        // Hiển thị mô tả truyện
        TextView book_description_content = findViewById(R.id.book_description_content);
        book_description_content.setText(truyen_mota);

        // Hiển thị image truyện
        ImageView book_image = findViewById(R.id.book_image);
        int imageResource = getResources().getIdentifier(truyen_image, "drawable", getPackageName());
        book_image.setImageResource(imageResource);


        // Lấy LinearLayout trong XML để chèn các mục sách vào
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        // Lấy top 5 truyện theo thể loại
        databasedoctruyen = new MyDatabaseHelper(this);

        Cursor cursor = databasedoctruyen.GetTop5SachTheoTheLoai(theloai_ten);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int columnIndexTitle = cursor.getColumnIndex("truyen_tentruyen");
                int columnIndexAuthor = cursor.getColumnIndex("truyen_tentacgia");
                int columnIndexImage = cursor.getColumnIndex("truyen_image");
                int columnIndexMoTa = cursor.getColumnIndex("truyen_mota");

                String tentruyen = cursor.getString(columnIndexTitle);
                String tentacgia = cursor.getString(columnIndexAuthor);
                String image = cursor.getString(columnIndexImage);
                String mota = cursor.getString(columnIndexMoTa);

                // Tạo LinearLayout cho mỗi sách
                LinearLayout bookLayout = new LinearLayout(this);
                bookLayout.setOrientation(LinearLayout.VERTICAL);
                bookLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                bookLayout.setPadding(5, 5, 5, 5);

                // Tạo ImageView cho hình ảnh của sách
                ImageView bookImage = new ImageView(this);
                // Đảm bảo rằng đường dẫn đến hình ảnh hợp lệ
                bookImage.setImageResource(getResources().getIdentifier(image, "drawable", getPackageName()));
                bookImage.setLayoutParams(new LinearLayout.LayoutParams(400, 500));
                bookLayout.addView(bookImage);

                // Tạo TextView cho tên sách
                TextView bookTitle = new TextView(this);
                bookTitle.setText(tentruyen);
                bookTitle.setTextSize(14);
                bookTitle.setTextColor(Color.BLACK);
                bookTitle.setGravity(Gravity.CENTER);
                bookLayout.addView(bookTitle);

                // Tạo TextView cho tên tác giả
                TextView bookAuthor = new TextView(this);
                bookAuthor.setText(tentacgia);
                bookAuthor.setTextColor(Color.parseColor("#666666"));
                bookAuthor.setTextSize(12);
                bookAuthor.setGravity(Gravity.CENTER);
                bookLayout.addView(bookAuthor);

                // Thêm sự kiện click vào LinearLayout của sách
                bookLayout.setOnClickListener(v -> {
                    // Gửi dữ liệu qua Intent để chuyển sang màn hình chi tiết sách
                    Intent intent = new Intent(MainChiTietBookActivity.this, MainChiTietBookActivity.class);
                    intent.putExtra("truyen_tentruyen", tentruyen);
                    intent.putExtra("truyen_tentacgia", tentacgia);
                    intent.putExtra("truyen_image", image);
                    intent.putExtra("theloai_ten", theloai_ten);
                    intent.putExtra("truyen_mota", mota);
                    startActivity(intent);
                });

                // Thêm LinearLayout của mỗi sách vào LinearLayout chính
                linearLayout.addView(bookLayout);

            } while (cursor.moveToNext());
            cursor.close();
        }


        //Tạo sự kiện click button đang đọc khi chuyển sang màn hình đang đọc với Intent
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainChiTietBookActivity.this, MainBookListActivity.class);

                intent.putExtra("theloai_ten", theloai_ten);
                startActivity(intent);
            }
        });
    }
}
