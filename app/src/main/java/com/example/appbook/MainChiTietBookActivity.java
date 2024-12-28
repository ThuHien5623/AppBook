package com.example.appbook;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.content.SharedPreferences;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainChiTietBookActivity extends Activity {
    MyDatabaseHelper databasedoctruyen;
    ImageView back_button, heart_button;
    LinearLayout btnDocSach;
    private boolean isHeartSelected = false;
    private boolean isReadingSelected = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtin);

        back_button = findViewById(R.id.back_button);
        heart_button = findViewById(R.id.heart);
        btnDocSach = findViewById(R.id.btnDocSach);


        // Nhận dữ liệu từ Intent đuược truền từ mainbooklist
        String truyen_tentruyen = getIntent().getStringExtra("truyen_tentruyen");
        String truyen_tentacgia = getIntent().getStringExtra("truyen_tentacgia");
        String truyen_image = getIntent().getStringExtra("truyen_image");
        String theloai_ten = getIntent().getStringExtra("theloai_ten");
        String truyen_mota = getIntent().getStringExtra("truyen_mota");
        int truyen_id = getIntent().getIntExtra("truyen_id", -1); // Giá trị mặc định là -1
        int theloai_id = getIntent().getIntExtra("theloai_id", -1);


        Log.d("truyen", "truyen_id: " + truyen_id);
        Log.d("truyen", "truyen_tentacgia: " + truyen_tentacgia);
        Log.d("truyen", "truyen_image: " + truyen_image);
        Log.d("truyen", "theloai_ten: " + theloai_ten);
        Log.d("truyen", "truyen_mota: " + truyen_mota);
        Log.d("truyen", "truyen_tentruyen: " + truyen_tentruyen);
        Log.d("truyen", "theloai_id: " + theloai_id);


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
        //khơi tạo đoi tuong database
        databasedoctruyen = new MyDatabaseHelper(this);

        // Lấy top 5 truyện theo ten thể loại
        Cursor cursor = databasedoctruyen.GetTop5SachTheoTheLoai(theloai_ten);
        //    kiem tra du lieu k rõng va bat dau tu dong dau tien va bat dau vong lap while
        if (cursor != null && cursor.moveToFirst()) {
            do {
                //  tao chi muc cho tung cot du lieu
                int columnIndexTitle = cursor.getColumnIndex("truyen_tentruyen");
                int columnIndexAuthor = cursor.getColumnIndex("truyen_tentacgia");
                int columnIndexImage = cursor.getColumnIndex("truyen_image");
                int columnIndexMoTa = cursor.getColumnIndex("truyen_mota");
                int columnIndexTruyenID = cursor.getColumnIndex("truyen_id");
                //gan gia tri tung cot theo chi muc vao tung bien
                String tentruyen = cursor.getString(columnIndexTitle);
                String tentacgia = cursor.getString(columnIndexAuthor);
                String image = cursor.getString(columnIndexImage);
                String mota = cursor.getString(columnIndexMoTa);
                int truyenid = cursor.getInt(columnIndexTruyenID);

                // Tạo LinearLayout cho mỗi sách
                LinearLayout bookLayout = new LinearLayout(this);
                bookLayout.setOrientation(LinearLayout.VERTICAL);
                bookLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                bookLayout.setGravity(Gravity.CENTER);
                bookLayout.setPadding(30, 15, 30, 15);

                // Creating FrameLayout for image
                FrameLayout frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new LinearLayout.LayoutParams(330, 500));
                frameLayout.setBackgroundResource(R.drawable.bt_img); // Set background
                frameLayout.setClipToOutline(true);

                // Tạo ImageView cho hình ảnh của sách
                ImageView bookImage = new ImageView(this);
                bookImage.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                bookImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // Đảm bảo rằng đường dẫn đến hình ảnh hợp lệ
                bookImage.setImageResource(getResources().getIdentifier(image, "drawable", getPackageName()));
                frameLayout.addView(bookImage);

                // Adding FrameLayout to the main book layout
                bookLayout.addView(frameLayout);

                // Tạo TextView cho tên sách
                TextView bookTitle = new TextView(this);
                bookTitle.setText(tentruyen);
                bookTitle.setTextSize(20);
                bookTitle.setTextColor(Color.BLACK);
                bookTitle.setGravity(Gravity.CENTER);
                bookTitle.setTypeface(null, Typeface.BOLD);  // Make title bold
                bookLayout.addView(bookTitle);

                // Tạo TextView cho tên tác giả
                TextView bookAuthor = new TextView(this);
                bookAuthor.setText(tentacgia);
                bookAuthor.setTextSize(18);
                bookAuthor.setTextColor(Color.BLACK);
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
                    intent.putExtra("truyen_id", truyenid);
                    startActivity(intent);
                });

                // Thêm LinearLayout của mỗi sách vào LinearLayout chính
                linearLayout.addView(bookLayout);

            } while (cursor.moveToNext());
            cursor.close();
        }

        // Sự kiện click nút trở về
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainChiTietBookActivity.this, MainBookListActivity.class);

                intent.putExtra("theloai_ten", theloai_ten);
                startActivity(intent);
            }
        });


        // Lấy taikhoan_id từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AppBookPrefs", MODE_PRIVATE);
        int idtaikhoan = sharedPreferences.getInt("idtaikhoan", -1);  // Lấy ID người dùng từ SharedPreferences

        Log.d("MainChiTietBookActivity", "idtaikhoan" + idtaikhoan);  // Log số lượng bản ghi trong cursor

        // Kiểm tra nếu taikhoan_id hợp lệ
        if (idtaikhoan != -1) {
            // Kiểm tra xem truyện đã có trong yêu thích chưa
            Cursor cursoryeuthich = databasedoctruyen.getFavorites(idtaikhoan, truyen_id);
            boolean isFavorite = false;

            if (cursoryeuthich != null && cursoryeuthich.moveToFirst()) {
                do {
                    int columnIndexTruyenID = cursoryeuthich.getColumnIndex("truyen_id");
                    int truyenid = cursoryeuthich.getInt(columnIndexTruyenID);

                    if (truyenid == truyen_id) {
                        isFavorite = true;
                        break;
                    }
                } while (cursoryeuthich.moveToNext());
                cursoryeuthich.close();
            }

            // Cập nhật hình ảnh của nút tim dựa trên trạng thái yêu thích
            if (isFavorite) {
                heart_button.setImageResource(R.drawable.tim);  // Hình ảnh tim đã chọn
                isHeartSelected = true;  // Set trạng thái tim là đã chọn
            } else {
                heart_button.setImageResource(R.drawable.baseline_favorite_24);  // Hình ảnh tim chưa chọn
                isHeartSelected = false;  // Set trạng thái tim là chưa chọn
            }
        }

        // Sự kiện click vào tim
        heart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Chuyển đổi giữa hai hình ảnh tim
                if (isHeartSelected) {
                    // Kiểm tra nếu truyện đã có trong yêu thích thì mới xóa
                        heart_button.setImageResource(R.drawable.baseline_favorite_24);  // Hình ảnh tim chưa chọn
                        // Xóa dữ liệu khỏi bảng yêu thích
                        databasedoctruyen.removeFromFavorites(idtaikhoan, truyen_id);
                } else {
                    heart_button.setImageResource(R.drawable.tim);  // Hình ảnh tim đã chọn
                    // Thêm dữ liệu vào bảng yêu thích
                    databasedoctruyen.addToFavorites(idtaikhoan, truyen_id);
                }

                // Đổi trạng thái của biến isHeartSelected
                isHeartSelected = !isHeartSelected;
            }
        });


        // Sự kiện click vào đọc sách
        btnDocSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra nếu taikhoan_id hợp lệ
                if (idtaikhoan != -1) {
                    // Kiểm tra xem truyện đã có trong bảng "đang đọc" chưa
                    Cursor cursordangdoc = databasedoctruyen.getReadingList(idtaikhoan, truyen_id);
                    boolean isReading = false;

                    if (cursordangdoc != null && cursordangdoc.moveToFirst()) {
                        do {
                            int columnIndexTruyenID = cursordangdoc.getColumnIndex("truyen_id");
                            int truyenid = cursordangdoc.getInt(columnIndexTruyenID);

                            if (truyenid == truyen_id) {
                                isReading = true;
                                break;
                            }
                        } while (cursordangdoc.moveToNext());
                        cursordangdoc.close();
                    }

                    if (!isReading) {
                        // Nếu chưa có trong bảng "đang đọc", thêm vào bảng
                        databasedoctruyen.addToReadingList(idtaikhoan, truyen_id);

                        // Tăng thêm 1 lượt xem trong bảng tbTruyen
                        databasedoctruyen.incrementViewCount(truyen_id);

                        Log.d("MainChiTietBookActivity", "Đã thêm truyện vào bảng đang đọc và tăng lượt xem");
                    } else {
                        Log.d("MainChiTietBookActivity", "Truyen da co trong bang dang dọc");
                    }
                }

                // Lấy link sách
                Cursor cursorlinksach = databasedoctruyen.getLinkSach(truyen_id);

                if (cursorlinksach != null && cursorlinksach.moveToFirst()) {
                    do {
                        int columnIndexTruyenLink = cursorlinksach.getColumnIndex("truyen_link");
                        String truyen_link = cursorlinksach.getString(columnIndexTruyenLink);

                        Log.d("MainChiTietBookActivity", "Truyen_link" + truyen_link);

                        Intent intent = new Intent(MainChiTietBookActivity.this, MainDocSachActivity.class);
                        intent.putExtra("truyen_tentruyen", truyen_tentruyen);
                        intent.putExtra("truyen_tentacgia", truyen_tentacgia);
                        intent.putExtra("truyen_image", truyen_image);
                        intent.putExtra("theloai_ten", theloai_ten);
                        intent.putExtra("truyen_mota", truyen_mota);
                        intent.putExtra("truyen_id", truyen_id);
                        intent.putExtra("theloai_id", theloai_id);
                        intent.putExtra("truyen_link", truyen_link);

                        startActivity(intent);

                    } while (cursorlinksach.moveToNext());
                    cursorlinksach.close();
                }




            }
        });


    }
}
