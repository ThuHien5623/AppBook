package com.example.appbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appbook.model.TaiKhoan;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "BookLibrary.db";
    public static final int DATABASE_VERSION = 1;

    //Bảng tài khoản
    public static final String TABLE_TAIKHOAN = "tbTaiKhoan";
    public static final String TAIKHOAN_ID = "taikhoan_id";
    public static final String TAIKHOAN_TENDANGNHAP = "taikhoan_tendangnhap";
    public static final String TAIKHOAN_MATKHAU = "taikhoan_matkhau";
    public static final String TAIKHOAN_SDT = "taikhoan_sdt";
    public static final String TAIKHOAN_PHANQUYEN = "taikhoan_phanquyen";

    //Bảng truyện
    public static final String TABLE_TRUYEN = "tbTruyen";
    public static final String TRUYEN_ID = "truyen_id";
    public static final String TRUYEN_TENTRUYEN = "truyen_tentruyen";
    public static final String TRUYEN_TENTACGIA = "truyen_tentacgia";
    public static final String TRUYEN_MOTA = "truyen_mota";
    public static final String TRUYEN_IMAGE = "truyen_image";
    public static final String TRUYEN_LINK = "truyen_link";
    public static final String TRUYEN_THELOAI_ID = "theloai_id";
    public static final String TRUYEN_TRANGTHAI = "truyen_trangthai";
    public static final String TRUYEN_LUOTXEM = "truyen_luotxem";
    public static final String TRUYEN_NGAYDANG = "truyen_ngaydang";
    public static final String TRUYEN_NGAYCAPNHATCUOI = "truyen_ngaycapnhatcuoi";

    //Bảng thể loại truyện
    public static final String TABLE_THELOAI = "tbTheLoai";
    public static final String THELOAI_ID = "theloai_id";
    public static final String THELOAI_TEN = "theloai_ten";

    //Bảng truyện yêu thích
    public static final String TABLE_YEUTHICH = "tbYeuThich";
    public static final String YEUTHICH_ID = "yeuthich_id";
    public static final String YEUTHICH_TAIKHOAN_ID = "taikhoan_id";
    public static final String YEUTHICH_TRUYEN_ID = "truyen_id";
    public static final String YEUTHICH_NGAYTHEM = "yeuthich_ngaythem";

    //Bảng truyện đang đọc
    public static final String TABLE_ĐANGDOC = "tbDangDoc";
    public static final String DANGDOC_ID = "dangdoc_id";
    public static final String DANGDOC_TAIKHOAN_ID = "taikhoan_id";
    public static final String DANGDOC_TRUYEN_ID = "truyen_id";
    public static final String DANGDOC_NGAYTHEM = "dangdoc_ngaythem";

    // Tạo bảng tại phương thức này
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
    }

    // Tạo bảng table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbTaiKhoan = "CREATE TABLE " + TABLE_TAIKHOAN + " ( " + TAIKHOAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TAIKHOAN_TENDANGNHAP + " TEXT, " + TAIKHOAN_MATKHAU + " TEXT, " + TAIKHOAN_SDT + " TEXT, "
                + TAIKHOAN_PHANQUYEN + " INTEGER )";

        String tbTruyen = "CREATE TABLE " + TABLE_TRUYEN + " ( " + TRUYEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRUYEN_TENTRUYEN + " TEXT, " + TRUYEN_TENTACGIA + " TEXT, " + TRUYEN_MOTA + " TEXT, "
                + TRUYEN_IMAGE + " TEXT, " + TRUYEN_LINK + " TEXT, " + TRUYEN_THELOAI_ID + " INTEGER, " + TRUYEN_TRANGTHAI + " TEXT, " + TRUYEN_LUOTXEM + " INTEGER, "
                + TRUYEN_NGAYDANG + " DATETIME, " + TRUYEN_NGAYCAPNHATCUOI + " DATETIME )";

        String tbTheLoai = "CREATE TABLE " + TABLE_THELOAI + " ( " + THELOAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + THELOAI_TEN + " TEXT )";

        String tbYeuThich = "CREATE TABLE " + TABLE_YEUTHICH + " ( " + YEUTHICH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + YEUTHICH_TAIKHOAN_ID + " INTEGER, " + YEUTHICH_TRUYEN_ID + " INTEGER, " + YEUTHICH_NGAYTHEM + " DATETIME )";

        String tbDangDoc = "CREATE TABLE " + TABLE_ĐANGDOC + " ( " + DANGDOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DANGDOC_TAIKHOAN_ID + " INTEGER, " + DANGDOC_TRUYEN_ID + " INTEGER, " + DANGDOC_NGAYTHEM + " DATETIME )";


        // Thêm tên thể loại
        String theloai_tieuthuyet = "INSERT INTO tbTheLoai VAlUES (null,'Tiểu thuyết')";
        String theloai_vanhoc = "INSERT INTO tbTheLoai VAlUES (null,'Văn học')";
        String theloai_truyendangian = "INSERT INTO tbTheLoai VAlUES (null,'Truyện dân gian')";
        String theloai_giaoduc = "INSERT INTO tbTheLoai VAlUES (null,'Giáo dục')";
        String theloai_truyenngan = "INSERT INTO tbTheLoai VAlUES (null,'Truyện ngắn')";

        // Thêm tên thể loại
        String sach_tieuthuyet_matbiec = "INSERT INTO tbTruyen VAlUES (null,'Mắt biếc','Nguyễn Nhật Ánh','Mắt biếc là một tiểu thuyết lãng mạn nổi tiếng của nhà văn Nguyễn Nhật Ánh. Câu chuyện xoay quanh tình yêu đơn phương của Ngạn, một chàng trai quê, dành cho Hà Lan, cô bạn có đôi mắt biếc cuốn hút từ thuở nhỏ. Tuy nhiên, Hà Lan lại yêu một người khác và rời xa làng quê, để lại Ngạn với nỗi nhớ và tình cảm không bao giờ được đáp lại. Mắt biếc không chỉ là câu chuyện về tình yêu mà còn là nỗi tiếc nuối của những ký ức tuổi thơ, sự thay đổi của con người qua thời gian.','matbiec',null,1,null,null,'2024-11-20','2024-11-20')";
        String sach_tieuthuyet_ongnoiphieuluyki = "INSERT INTO tbTruyen VALUES (null, 'Ông Nội Phiêu Lưu Ký', 'Dương Khuê', '“Ông Nội Phiêu Lưu Ký” là một câu chuyện thú vị và hấp dẫn về những cuộc phiêu lưu kỳ thú của một ông lão và những đứa trẻ trong gia đình. Qua đó, tác phẩm truyền tải những bài học về tình yêu thương, sự kiên trì và lòng dũng cảm.', 'ongnoiphieuluuky', 1, null, null, '2024-11-21', '2024-11-21')";
        String sach_tieuthuyet_chipheo = "INSERT INTO tbTruyen VALUES (null,'Chí Phèo','Nam Cao','Chí Phèo là một tác phẩm nổi tiếng của Nam Cao, phản ánh cuộc sống của người nông dân nghèo khổ dưới chế độ phong kiến. Câu chuyện xoay quanh bi kịch của Chí Phèo, một người đàn ông bị xã hội xua đuổi, đã đánh mất nhân phẩm và trở thành kẻ hung dữ. Tuy nhiên, trong con người anh vẫn tiềm ẩn những yếu tố của tình yêu và lòng nhân ái, tạo nên một câu chuyện đầy đau đớn về xã hội và số phận con người.','chipheo',null,1,null,null,'2024-11-21','2024-11-21')";
        String sach_tieuthuyet_doithua = "INSERT INTO tbTruyen VALUES (null,'Đời thừa','Nam Cao','Đời thừa là một tiểu thuyết kinh điển của Nam Cao về cuộc sống của những con người nghèo khổ trong xã hội cũ. Qua những nhân vật chính là Hộ và bà vợ, Nam Cao đã khắc họa một cách sâu sắc về số phận và sự chán nản, tuyệt vọng của con người trong một xã hội thiếu công bằng và nhân đạo.','doithua',null,1,null,null,'2024-11-22','2024-11-22')";
        String sach_tieuthuyet_hoangtu = "INSERT INTO tbTruyen VALUES (null,'Hoàng tử bé','Antoine de Saint-Exupéry','Hoàng tử bé là một trong những cuốn tiểu thuyết nổi tiếng nhất trên thế giới. Tác phẩm kể về cuộc hành trình của một hoàng tử đến thăm các hành tinh khác nhau và gặp gỡ những con người kỳ lạ. Qua đó, tác giả khắc họa những bài học sâu sắc về cuộc sống, tình yêu và sự trưởng thành.','hoangtube',null,1,null,null,'2024-11-21','2024-11-21')";
        String sach_tieuthuyet_dacnhantam = "INSERT INTO tbTruyen VALUES (null, 'Đắc nhân tâm', 'Dale Carnegie', 'Đắc nhân tâm là cuốn sách kinh điển về nghệ thuật giao tiếp và tạo dựng mối quan hệ. Cuốn sách đã giúp hàng triệu người thay đổi cuộc sống của họ thông qua việc cải thiện kỹ năng giao tiếp.', 'dacnhantam', null,2, null, null, '2024-11-25', '2024-11-25')";
        String sach_tieuthuyet_toithayhoavangtrencoxanh = "INSERT INTO tbTruyen VALUES (null,'Tôi thấy hoa vàng trên cỏ xanh','Nguyễn Nhật Ánh','Tôi thấy hoa vàng trên cỏ xanh là một tiểu thuyết về tuổi thơ của Ngạn và những người bạn của anh tại một làng quê Việt Nam. Câu chuyện đậm chất thơ và giàu tính nhân văn, mang đến cho độc giả những cảm xúc chân thật về tình bạn, tình yêu và những kỷ niệm khó quên. Cuốn sách là sự hoài niệm về những ngày tháng trong sáng, tươi đẹp của tuổi trẻ.','hoavangtrencoxanh',null,1,null,null,'2024-11-24','2024-11-24')";
        String sach_vanhoc_harrypotter = "INSERT INTO tbTruyen VALUES (null,'Harry Potter và Hòn đá Phù thủy','J.K. Rowling','Harry Potter và Hòn đá Phù thủy là phần đầu tiên trong loạt tiểu thuyết nổi tiếng của J.K. Rowling. Câu chuyện kể về hành trình của Harry Potter từ một cậu bé mồ côi bình thường đến việc phát hiện mình là phù thủy và bước vào thế giới phép thuật tại trường Hogwarts. Tại đây, Harry gặp gỡ những người bạn thân thiết và khám phá nhiều bí mật về quá khứ của mình.','harrypotter',null,2,null,null,'2024-11-25','2024-11-25')";
        String sach_vanhoc_tatden = "INSERT INTO tbTruyen VALUES (null,'Tắt đèn','Ngô Tất Tố','Tắt đèn là một tác phẩm phản ánh sâu sắc đời sống khốn khổ của những người nông dân trong xã hội phong kiến Việt Nam. Câu chuyện kể về cuộc sống của nhân vật chính, chị Dậu, trong những điều kiện sống nghèo khó và đầy áp bức. Từ đó, tác phẩm phê phán sự bất công trong xã hội và khắc họa tinh thần đấu tranh của con người.','tatden',null,2,null,null,'2024-11-26','2024-11-26')";
        String sach_vanhoc_sodo = "INSERT INTO tbTruyen VALUES (null,'Số đỏ','Vũ Trọng Phụng','Số đỏ là một tiểu thuyết mang tính trào phúng, phản ánh hiện thực xã hội Việt Nam trong giai đoạn xã hội cũ. Câu chuyện xoay quanh nhân vật chính Xuân, người trải qua những biến cố và nghịch cảnh trong đời sống, thể hiện những quan điểm, góc nhìn châm biếm về xã hội thời bấy giờ. Tác phẩm phản ánh sự vô lý, bất công và mâu thuẫn của xã hội.','sodo',null,2,null,null,'2024-11-27','2024-11-27')";
        String sach_vanhoc_nhatky = "INSERT INTO tbTruyen VALUES (null,'Nhật ký trong tù','Hồ Chí Minh','Nhật ký trong tù là tập hợp những bài thơ của Hồ Chí Minh trong thời gian người bị giam cầm tại Trung Quốc. Tập thơ phản ánh một cách sinh động cuộc sống khổ cực trong tù và tinh thần kiên cường, bất khuất của Người. Các bài thơ trong sách là những suy tư, những lời tâm sự về cuộc sống, tình yêu quê hương đất nước.','nhatky',null,2,null,null,'2024-11-28','2024-11-28')";
        String sach_vanhoc_sudai = "INSERT INTO tbTruyen VALUES (null, 'Sự Dại', 'Tô Hoài', 'Sự Dại là cuốn tiểu thuyết nổi tiếng của nhà văn Tô Hoài, với phong cách kể chuyện mộc mạc, gần gũi nhưng lại rất sâu sắc trong việc phản ánh tâm lý con người và những biến động của xã hội trong một thời kỳ lịch sử đặc biệt.', 'sudai',null, 2, null, null, '2024-11-21', '2024-11-21')";
        String sach_truyendangian_traucham = "INSERT INTO tbTruyen VALUES (null,'Trâu chậm uống nước đục','Tác giả dân gian','Trâu chậm uống nước đục là một câu chuyện ngụ ngôn nổi tiếng, dạy bài học về sự khôn ngoan và cẩn trọng. Trong câu chuyện, nhân vật trâu chậm biết chọn lựa thời điểm thích hợp để tìm nước, và đã tránh được những nguy hiểm bất ngờ. Câu chuyện này mang đến thông điệp về sự kiên nhẫn và khéo léo trong cuộc sống.','traucham',null,3,null,null,'2024-12-01','2024-12-01')";
        String sach_truyendangian_concoc = "INSERT INTO tbTruyen VALUES (null,'Con cóc là cậu ông trời','Tác giả dân gian','Câu chuyện về con cóc là một trong những truyện dân gian nổi tiếng, kể về hành trình của con cóc nhỏ bé nhưng dũng cảm. Con cóc đã dạy cho mọi người bài học về sự khiêm tốn và lòng trung thực trong cuộc sống.','concoc',null,3,null,null,'2024-12-02','2024-12-02')";
        String sach_truyendangian_chucuoi = "INSERT INTO tbTruyen VALUES (null,'Sự tích chú Cuội ngồi gốc cây đa','Tác giả dân gian','Sự tích chú Cuội ngồi gốc cây đa là một trong những câu chuyện cổ tích Việt Nam nổi tiếng, giải thích về sự ra đời của cây đa và truyền thuyết chú Cuội. Đây là câu chuyện về lòng nhân ái và sự lừa dối, tạo nên một bài học về đạo đức trong xã hội.','chucuoi',null,3,null,null,'2024-12-03','2024-12-03')";
        String sach_truyendangian_tamcam = "INSERT INTO tbTruyen VALUES (null, 'Tấm Cám', 'Truyền thuyết dân gian', 'Tấm Cám là câu chuyện cổ tích nổi tiếng trong kho tàng văn học dân gian Việt Nam. Câu chuyện kể về Tấm, một cô gái hiền lành, nhân hậu nhưng phải chịu nhiều sự bất công từ người mẹ kế và cô em gái Cám độc ác. Tấm đã trải qua bao khó khăn và thử thách, từ việc bị mẹ kế hãm hại đến việc phải nhờ đến sự trợ giúp của các thế lực thần bí để chiến thắng và tìm lại hạnh phúc. Truyện mang thông điệp về sự kiên nhẫn, lòng tốt và công lý.', 'tamcam',null, 3, null, null, '2024-11-25', '2024-11-25')";
        String sach_truyendangian_caytre = "INSERT INTO tbTruyen VALUES (null, 'Cây tre trăm đốt', 'Truyền thuyết dân gian', 'Cây tre trăm đốt là một câu chuyện dân gian nổi tiếng, nói về một người nghèo khó sống trong một ngôi làng. Trong một lần đi qua rừng, anh đã gặp một cây tre kỳ lạ có thể mọc lên tới trăm đốt. Nhờ vào cây tre này, anh đã giúp đỡ được dân làng và thay đổi cuộc đời mình. Câu chuyện mang thông điệp về sự kiên nhẫn và lòng dũng cảm khi đối mặt với khó khăn trong cuộc sống.', 'caytre',null, 3, null, null, '2024-11-25', '2024-11-25')";
        String sach_truyendangian_cobebandiem = "INSERT INTO tbTruyen VALUES (null, 'Cô bé bán diêm', 'Hans Christian Andersen', 'Cô bé bán diêm là một câu chuyện nổi tiếng của Hans Christian Andersen kể về một cô bé nghèo khó, phải bán diêm trong đêm giao thừa lạnh giá. Dù không bán được diêm nào, cô bé vẫn có những ước mơ đẹp và cuối cùng tìm thấy sự bình yên trong giấc mơ của mình. Câu chuyện mang thông điệp về sự hi sinh, lòng trắc ẩn và hy vọng trong cuộc sống.', 'cobebandiem',null, 3, null, null, '2024-11-25', '2024-11-25')";
        String sach_anhmeovanvacochimen = "INSERT INTO tbTruyen VALUES (null, 'Anh Mèo Vằn Và Cô Chim Én', 'Nguyễn Nhật Ánh', '“Anh Mèo Vằn và Cô Chim Én” là một câu chuyện dễ thương về tình bạn và sự đồng cảm giữa các loài vật. Mèo Vằn và Chim Én, dù có những khác biệt về bản tính và cuộc sống, vẫn tìm thấy điểm chung và kết nối nhau qua những cuộc phiêu lưu thú vị. Cuốn sách mang đến cho độc giả những bài học quý giá về tình bạn, sự hiểu biết và tôn trọng lẫn nhau, từ đó khuyến khích tình yêu thương và sự hòa hợp trong cộng đồng.', 'anhmeovanvacochimen',null, 3, null, null, '2024-11-22', '2024-11-22')";
        String sach_giaoduc_nguoivietnam = "INSERT INTO tbTruyen VALUES (null, 'Người Việt Nam', 'Nguyễn Trường Tộ', 'Nguyễn Trường Tộ là một trí thức kiệt xuất, người đã có những đóng góp quan trọng vào việc thúc đẩy cải cách đất nước trong thời kỳ cuối thế kỷ XIX. Ông nổi bật với tầm nhìn và trí tuệ vượt thời đại, luôn kêu gọi cải cách đất nước để nâng cao sức mạnh quốc gia. Những đề xuất của ông về giáo dục, quân sự và phát triển công nghiệp thương mại vẫn còn giá trị cho đến ngày nay.', 'nguoivietnam',null, 4, null, null, '2024-12-01', '2024-12-01')";
        String sach_giaoduc_suynghialamgiau = "INSERT INTO tbTruyen VALUES (null, 'Suy nghĩ, làm giàu', 'Napoleon Hill', 'Suy nghĩ, làm giàu là cuốn sách nổi tiếng giúp người đọc hiểu được những nguyên lý và bí quyết để đạt được thành công trong công việc và cuộc sống. Cuốn sách chia sẻ những nguyên lý cơ bản của sự giàu có và hạnh phúc mà mọi người có thể áp dụng để đạt được mục tiêu.', 'suynghialamgiau',null, 4, null, null, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_chagiouchangheo = "INSERT INTO tbTruyen VALUES (null, 'Cha giàu, cha nghèo', 'Robert T. Kiyosaki', 'Cha giàu, cha nghèo là một cuốn sách giúp người đọc nhận thức rõ về sự khác biệt giữa cách tư duy của những người giàu và nghèo. Cuốn sách hướng dẫn về cách quản lý tài chính và đầu tư để đạt được sự tự do tài chính trong tương lai.', 'chagiouchangheo',null, 4, null, null, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_tuduynhanhvacham = "INSERT INTO tbTruyen VALUES (null, 'Tư duy nhanh và chậm', 'Daniel Kahneman', 'Tư duy nhanh và chậm của Daniel Kahneman giải thích cách mà con người ra quyết định trong các tình huống khác nhau, với sự phân chia rõ ràng giữa hai loại tư duy: tư duy nhanh (instinctive) và tư duy chậm (logical). Cuốn sách này giúp người đọc hiểu rõ hơn về các quyết định trong cuộc sống và công việc.', 'tuduynhanhvacham',null, 4, null, null, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_nguoibanhang = "INSERT INTO tbTruyen VALUES (null, 'Người bán hàng vĩ đại nhất thế giới', 'Og Mandino', 'Người bán hàng vĩ đại nhất thế giới là một cuốn sách dạy về nghệ thuật bán hàng và tự cải thiện bản thân. Cuốn sách mang lại những bài học quý giá về cách thức giao tiếp, thuyết phục và quản lý bán hàng hiệu quả.', 'nguoibanhang',null, 4, null, null, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_bimatcuathanhcong = "INSERT INTO tbTruyen VALUES (null, 'Bí mật của thành công', 'Og Mandino', 'Bí mật của thành công là cuốn sách cổ điển về việc đạt được thành công trong cuộc sống thông qua một quy trình tư duy và hành động có phương pháp. Cuốn sách này sẽ giúp bạn tìm ra các chìa khóa để thành công trong công việc và cuộc sống cá nhân.', 'bimatcuathanhcong',null, 4, null, null, '2024-11-25', '2024-11-25')";
        String sach_truyenngan_laohac = "INSERT INTO tbTruyen VALUES (null, 'Lão Hạc', 'Nam Cao', 'Lão Hạc là một tác phẩm nổi tiếng của nhà văn Nam Cao, kể về cuộc sống khốn khổ của một lão nông nghèo trong xã hội phong kiến. Lão Hạc sống một mình trong căn nhà nghèo khó và chăm sóc con chó cưng. Mặc dù sống trong cảnh nghèo đói, lão vẫn luôn yêu thương và chăm sóc con vật như người bạn tri kỷ. Tuy nhiên, sau khi con chó chết, Lão Hạc đã tìm đến cái chết để giải thoát cho chính mình và con chó. Câu chuyện phản ánh sự bất công của xã hội phong kiến và là nỗi khổ của những con người nghèo trong cuộc sống đầy bi kịch.', 'laohac',null, 5, null, null, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_langlesapa = "INSERT INTO tbTruyen VALUES (null, 'Lặng Lẽ Sapa', 'Nguyễn Thành Long', 'Lặng Lẽ Sapa là một tác phẩm nổi tiếng của nhà văn Nguyễn Thành Long, kể về câu chuyện của những con người sống trong những hoàn cảnh đặc biệt tại Sapa, một vùng núi cao, hẻo lánh. Trong tác phẩm, nhân vật chính là một bác sĩ trẻ làm việc tại Sapa, nơi anh gặp những người dân và những du khách qua lại. Câu chuyện thể hiện lòng kiên trì, sự hy sinh của những người làm công tác cứu chữa bệnh cho dân tộc, đồng thời phác họa những giá trị nhân văn về cuộc sống con người trong xã hội. Tác phẩm mang lại những suy ngẫm sâu sắc về cuộc sống con người và thiên nhiên.', 'langlesapa',null, 5, null, null, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_rungxanu = "INSERT INTO tbTruyen VALUES (null, 'Rừng Xà Nu', 'Nguyễn Trung Thành', 'Rừng Xà Nu là một tác phẩm nổi tiếng của Nguyễn Trung Thành, kể về cuộc sống và những hy sinh của người dân Tây Nguyên trong cuộc kháng chiến chống Pháp. Câu chuyện phản ánh sức mạnh của tình đoàn kết và lòng dũng cảm của các thế hệ người dân Tây Nguyên trong cuộc chiến đấu vì độc lập, tự do của dân tộc. Tác phẩm không chỉ phản ánh những khó khăn, gian khổ mà còn thể hiện lòng yêu nước, tinh thần quật cường của con người Việt Nam. Đặc biệt, rừng xà nu, với sức sống mãnh liệt của những cây cổ thụ, trở thành biểu tượng của sự trường tồn của dân tộc.', 'rungxanu',null, 5, null, null, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_haiduatre = "INSERT INTO tbTruyen VALUES (null, 'Hai Đứa Trẻ', 'Thạch Lam', 'Hai Đứa Trẻ là một tác phẩm nổi tiếng của nhà văn Thạch Lam, kể về hai đứa trẻ sống trong một gia đình nghèo. Câu chuyện phản ánh những khó khăn mà họ phải đối mặt trong cuộc sống, cũng như sự yêu thương, sự quan tâm mà gia đình dành cho nhau trong hoàn cảnh khó khăn. Tác phẩm mang đậm tính nhân văn, thể hiện lòng nhân ái, sự sẻ chia và tình yêu thương giữa những con người trong cuộc sống thường nhật. Đặc biệt, hình ảnh hai đứa trẻ gợi lên những suy ngẫm về cuộc sống và sự trưởng thành.', 'haiduatre',null, 5, null, null, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_leuchong = "INSERT INTO tbTruyen VALUES (null, 'Lều Chõng', 'Nguyễn Công Hoan', 'Lều Chõng là một tác phẩm nổi bật của Nguyễn Công Hoan, phản ánh những mâu thuẫn trong xã hội phong kiến qua hình ảnh nhân vật lều chõng - nơi mà các học trò nghèo phải ngồi học, nhưng cũng là biểu tượng cho sự nghèo khổ và khốn khó. Câu chuyện kể về cuộc sống khổ cực của những người học trò nghèo nhưng luôn khát khao học hỏi và vươn lên trong xã hội. Tuy nhiên, tác phẩm cũng không thiếu sự trào phúng, mỉa mai những bất công trong xã hội phong kiến.', 'leuchong',null, 5, null, null, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_banthanhpho = "INSERT INTO tbTruyen VALUES (null, 'Bạn Thành Phố', 'Nguyễn Nhật Ánh', '“Bạn Thành Phố” là một câu chuyện đầy cảm xúc về tình bạn, tình yêu và sự trưởng thành của những đứa trẻ trong môi trường đô thị hiện đại. Câu chuyện xoay quanh nhóm bạn nhỏ, khi những biến cố trong cuộc sống khiến họ phải đối mặt với những thay đổi lớn, từ việc chuyển trường cho đến việc tìm kiếm con đường riêng cho mình. Cuốn sách không chỉ là những dòng chữ về tình bạn thuần khiết mà còn phản ánh những giá trị nhân văn sâu sắc về tình yêu, sự hy sinh và lòng kiên cường trong cuộc sống.', 'banthanhpho',null, 5, null, null, '2024-11-22', '2024-11-22')";
        String sach_truyenngan_noikhurungchamtoithienvisao = "INSERT INTO tbTruyen VALUES (null, 'Nơi Khu Rừng Chạm Tới Những Vì Sao', 'Lê Thị Thanh Hương', '“Nơi Khu Rừng Chạm Tới Những Vì Sao” là một câu chuyện kỳ diệu, nơi những phép màu và bí ẩn thiên nhiên kết hợp với những giấc mơ của những đứa trẻ. Trong khu rừng huyền bí, các nhân vật chính – một nhóm trẻ em tò mò – khám phá những điều kỳ diệu xảy ra khi họ tìm cách chạm đến các vì sao. Cuốn sách không chỉ là một cuộc phiêu lưu đầy màu sắc mà còn chứa đựng thông điệp về sự hiếu kỳ, khám phá và niềm tin vào những điều tưởng chừng như không thể.', 'noikhurungchamtoinhungvisao',null, 5, null, null, '2024-11-22', '2024-11-22')";


        db.execSQL(tbTaiKhoan);
        db.execSQL(tbTruyen);
        db.execSQL(tbTheLoai);
        db.execSQL(tbYeuThich);
        db.execSQL(tbDangDoc);

        // Thêm tên thể loại vào Database
        db.execSQL(theloai_tieuthuyet);
        db.execSQL(theloai_vanhoc);
        db.execSQL(theloai_truyendangian);
        db.execSQL(theloai_giaoduc);
        db.execSQL(theloai_truyenngan);

        // Thêm tên sách vào Database
        db.execSQL(sach_tieuthuyet_matbiec);
        db.execSQL(sach_tieuthuyet_ongnoiphieuluyki);
        db.execSQL(sach_tieuthuyet_chipheo);
        db.execSQL(sach_tieuthuyet_doithua);
        db.execSQL(sach_tieuthuyet_hoangtu);
        db.execSQL(sach_tieuthuyet_dacnhantam);
        db.execSQL(sach_tieuthuyet_toithayhoavangtrencoxanh);
        db.execSQL(sach_vanhoc_harrypotter);
        db.execSQL(sach_vanhoc_tatden);
        db.execSQL(sach_vanhoc_sodo);
        db.execSQL(sach_vanhoc_nhatky);
        db.execSQL(sach_vanhoc_sudai);
        db.execSQL(sach_truyendangian_traucham);
        db.execSQL(sach_truyendangian_concoc);
        db.execSQL(sach_truyendangian_chucuoi);
        db.execSQL(sach_truyendangian_tamcam);
        db.execSQL(sach_truyendangian_caytre);
        db.execSQL(sach_truyendangian_cobebandiem);
        db.execSQL(sach_anhmeovanvacochimen);
        db.execSQL(sach_giaoduc_nguoivietnam);
        db.execSQL(sach_giaoduc_suynghialamgiau);
        db.execSQL(sach_giaoduc_chagiouchangheo);
        db.execSQL(sach_giaoduc_tuduynhanhvacham);
        db.execSQL(sach_giaoduc_nguoibanhang);
        db.execSQL(sach_giaoduc_bimatcuathanhcong);
        db.execSQL(sach_truyenngan_laohac);
        db.execSQL(sach_truyenngan_langlesapa);
        db.execSQL(sach_truyenngan_rungxanu);
        db.execSQL(sach_truyenngan_haiduatre);
        db.execSQL(sach_truyenngan_leuchong);
        db.execSQL(sach_truyenngan_banthanhpho);
        db.execSQL(sach_truyenngan_noikhurungchamtoithienvisao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }

    //Phương thức lấy tất cả tài khoản
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TAIKHOAN, null);
        return res;
    }

    //Phương thức add tài khoản vào database
    public void AddTaiKhoan(TaiKhoan taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Thực hiện insert thông qua ContentValues
        values.put(TAIKHOAN_TENDANGNHAP, taikhoan.getmTenDangNhap());
        values.put(TAIKHOAN_MATKHAU, taikhoan.getmMatKhau());
        values.put(TAIKHOAN_SDT, taikhoan.getmSDT());
        values.put(TAIKHOAN_PHANQUYEN, taikhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN, null, values);

        //Đóng lại khi không dùng
//        db.close();
        Log.e("ADD TK", "TC");
    }

    public Cursor getAccountByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tbTaiKhoan WHERE taikhoan_tendangnhap = ?", new String[]{username});
    }

    // Phương thức lấy tất cả thể loại truyện
    public Cursor getAllTheLoai() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_THELOAI, null);
    }

    // Phương thức Lấy truyện theo tên thể loại (dùng JOIN với tbTheLoai)
    public Cursor getBooksByCategory(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT t.* FROM " + TABLE_TRUYEN + " t " +
                        "JOIN " + TABLE_THELOAI + " tl ON t." + TRUYEN_THELOAI_ID + " = tl." + THELOAI_ID + " " +
                        "WHERE tl." + THELOAI_TEN + " = ?",
                new String[]{categoryName}
        );
    }

//    // Phương thức Lấy truyện theo tên ID thể loại
//    public Cursor getBooksByCategory(int categoryId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery(
//                "SELECT * FROM " + TABLE_TRUYEN + " WHERE " + TRUYEN_THELOAI_ID + " = ?",
//                new String[]{String.valueOf(categoryId)}
//        );
//    }

}
