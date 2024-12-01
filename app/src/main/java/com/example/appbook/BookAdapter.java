package com.example.appbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    private final Context context;
    private final List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        super(context, R.layout.item_book, books);
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        }

        Book book = books.get(position);

        TextView tensach = convertView.findViewById(R.id.txt_book_title);
        TextView tacgia = convertView.findViewById(R.id.txt_book_author);
        ImageView image = convertView.findViewById(R.id.img_book_cover);

        tensach.setText(book.getTensach());
        tacgia.setText(book.getTacgia());

        // Lấy tên tệp ảnh từ đối tượng Book
        String imageName = book.getImage(); // Giả sử bạn có phương thức getImage() trả về tên tệp ảnh, ví dụ: "book_cover_1"

        // Lấy ID của tài nguyên ảnh từ tên tệp
        int imageResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        // Nếu tài nguyên ảnh tồn tại, hiển thị nó
        if (imageResourceId != 0) {
            image.setImageResource(imageResourceId);
        }
//        else {
//            // Nếu không có ảnh, sử dụng ảnh mặc định
//            image.setImageResource(R.drawable.default_book_cover);
//        }

        return convertView;
    }
}
