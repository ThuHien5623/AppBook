package com.example.appbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainBookListActivity extends Activity {

    Button btn_reading, btn_favorite, btn_trangchu;
    EditText searchBar;
    TextView noResultsTextView;

    MyDatabaseHelper databasedoctruyen;
    List<Book> books;
    List<Book> allBooks; // Danh sách lưu toàn bộ sách theo thể loại
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        // Nhận dữ liệu từ Intent
        String tentheloai = getIntent().getStringExtra("theloai_ten");

        // Hiển thị tên thể loại
        TextView textView = findViewById(R.id.tvTenTheLoai);
        textView.setText(tentheloai);

        // Khởi tạo các View
        btn_reading = findViewById(R.id.btn_reading);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_trangchu = findViewById(R.id.btn_home);
        searchBar = findViewById(R.id.search_bar);
        noResultsTextView = findViewById(R.id.noResultsTextView);

        // Khởi tạo ListView và DatabaseHelper
        ListView listView = findViewById(R.id.listViewBooks);
        databasedoctruyen = new MyDatabaseHelper(this);
        books = new ArrayList<>();

        // Đổ danh sách sách theo thể loại
        loadBooksByCategory(tentheloai);

        // Gắn Adapter
        bookAdapter = new BookAdapter(this, books);
        listView.setAdapter(bookAdapter);

        // TextWatcher cho thanh tìm kiếm
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString().trim();

                if (query.isEmpty()) {
                    books.clear(); // Nếu không có từ khóa tìm kiếm, xóa tất cả sách
                    loadBooksByCategory(tentheloai); // Lấy lại danh sách sách ban đầu
                    bookAdapter.notifyDataSetChanged();
                } else {
                    // Tìm kiếm sách theo tên sách và tên thể loại
                    searchBooks(query, tentheloai);
                }
            }
        });

        // Xử lý sự kiện click cho các button
        btn_reading.setOnClickListener(view -> {
            Intent intent = new Intent(MainBookListActivity.this, MainDangDocActivity.class);
            startActivity(intent);
        });

        btn_favorite.setOnClickListener(view -> {
            Intent intent = new Intent(MainBookListActivity.this, MainYeuThichActivity.class);
            startActivity(intent);
        });

        btn_trangchu.setOnClickListener(view -> {
            Intent intent = new Intent(MainBookListActivity.this, MainTrangChuActivity.class);
            startActivity(intent);
        });

        // Xử lý sự kiện click cho từng sách trong ListView
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Book selectedBook = books.get(position);
            Intent intent = new Intent(MainBookListActivity.this, MainChiTietBookActivity.class);
            intent.putExtra("theloai_ten", tentheloai);
            intent.putExtra("truyen_tentruyen", selectedBook.getTensach());
            intent.putExtra("truyen_tentacgia", selectedBook.getTacgia());
            intent.putExtra("truyen_image", selectedBook.getImage());
            intent.putExtra("theloai_id", selectedBook.getTheloai_id());
            intent.putExtra("truyen_mota", selectedBook.getMota());
            startActivity(intent);
        });
    }

    // Hàm lấy danh sách sách theo thể loại
    private void loadBooksByCategory(String tentheloai) {
        Cursor cursor = databasedoctruyen.getBooksByCategory(tentheloai);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy chỉ mục của các cột từ cursor
                int columnIndexTitle = cursor.getColumnIndex("truyen_tentruyen");
                int columnIndexAuthor = cursor.getColumnIndex("truyen_tentacgia");
                int columnIndexImage = cursor.getColumnIndex("truyen_image");
                int columnIndextheloaiid = cursor.getColumnIndex("theloai_id");
                int columnIndexMoTa = cursor.getColumnIndex("truyen_mota");

                // Kiểm tra nếu cột tồn tại và lấy giá trị
                String tensach = "";
                String tacgia = "";
                String image = "";
                int theloai_id = 0;
                String mota = "";

                // Nếu cột tồn tại và không phải là -1 (tức là có cột này)
                if (columnIndexTitle != -1) {
                    tensach = cursor.getString(columnIndexTitle);
                }
                if (columnIndexAuthor != -1) {
                    tacgia = cursor.getString(columnIndexAuthor);
                }
                if (columnIndexImage != -1) {
                    image = cursor.getString(columnIndexImage);
                }
                if (columnIndextheloaiid != -1) {
                    theloai_id = cursor.getInt(columnIndextheloaiid);
                }
                if (columnIndexMoTa != -1) {
                    mota = cursor.getString(columnIndexMoTa);
                }

                books.add(new Book(tensach, tacgia, image,mota,theloai_id));
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Nếu không có sách, hiển thị thông báo không có kết quả
        if (books.isEmpty()) {
            noResultsTextView.setVisibility(View.VISIBLE);
        } else {
            noResultsTextView.setVisibility(View.GONE);
        }
    }

    // Hàm tìm kiếm sách theo từ khóa
    private void searchBooks(String query, String tentheloai) {
        books.clear(); // Xóa tất cả sách cũ

        Cursor cursorTimKiemSach = databasedoctruyen.TimKiemSachTheoTheLoai(query, tentheloai);

        if (cursorTimKiemSach != null && cursorTimKiemSach.moveToFirst()) {
            do {
                // Lấy chỉ mục của các cột từ cursor
                int columnIndexTitle = cursorTimKiemSach.getColumnIndex("truyen_tentruyen");
                int columnIndexAuthor = cursorTimKiemSach.getColumnIndex("truyen_tentacgia");
                int columnIndexImage = cursorTimKiemSach.getColumnIndex("truyen_image");
                int columnIndexMoTa = cursorTimKiemSach.getColumnIndex("truyen_mota");
                int columnIndextheloaiid = cursorTimKiemSach.getColumnIndex("theloai_id");


                // Kiểm tra nếu cột tồn tại và lấy giá trị
                String tensach = "";
                String tacgia = "";
                String image = "";
                int theloai_id = 0;
                String mota = "";

                // Nếu cột tồn tại và không phải là -1 (tức là có cột này)
                if (columnIndexTitle != -1) {
                    tensach = cursorTimKiemSach.getString(columnIndexTitle);
                }
                if (columnIndexAuthor != -1) {
                    tacgia = cursorTimKiemSach.getString(columnIndexAuthor);
                }
                if (columnIndexImage != -1) {
                    image = cursorTimKiemSach.getString(columnIndexImage);
                }
                if (columnIndextheloaiid != -1) {
                    theloai_id = cursorTimKiemSach.getInt(columnIndextheloaiid);
                }
                if (columnIndexMoTa != -1) {
                    mota = cursorTimKiemSach.getString(columnIndexMoTa);
                }

                books.add(new Book(tensach, tacgia, image,mota,theloai_id));
            } while (cursorTimKiemSach.moveToNext());

            cursorTimKiemSach.close();
        }

        // Cập nhật lại adapter sau khi tìm kiếm
        if (books.isEmpty()) {
            noResultsTextView.setVisibility(View.VISIBLE);
        } else {
            noResultsTextView.setVisibility(View.GONE);
        }
        bookAdapter.notifyDataSetChanged();
    }
}
