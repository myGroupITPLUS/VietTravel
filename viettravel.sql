-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 07, 2020 lúc 02:24 PM
-- Phiên bản máy phục vụ: 10.1.39-MariaDB
-- Phiên bản PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `appdulich`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `banner`
--

CREATE TABLE `banner` (
  `id` int(10) NOT NULL,
  `name` text NOT NULL,
  `descriptions` text NOT NULL,
  `url` text NOT NULL,
  `images` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `categoryname` varchar(200) NOT NULL,
  `descriptions` text NOT NULL,
  `images` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `categoryname`, `descriptions`, `images`) VALUES
(1, 'Hà Nội', 'Nhiều di tích lịch sử nổi tiếng như: Thành cổ Hà Nội, Tháp Rùa nằm giữa Hồ Hoàn Kiếm, Khu phố cổ Hà Nội, Ô Quan Chưởng, Thành Cổ Loa, Thăng long tứ trấn, Văn Miếu-Quốc Tử Giám, Làng gốm cổ truyền Bát Tràng, Chợ Gốm làng cổ Bát Tràng, Hỏa Lò, Nhà 48 Hàng Ngang, Nhà 5D Hàm Long.\r\n\r\nHà Nội có nhiều đền, chùa như: Chùa Một Cột, Chùa Quán Sứ, Chùa Trấn Quốc, chùa Non Nước, chùa Hương, Chùa Hoè Nhai, Chùa Láng, Chùa Liên Phái, Đền Ngọc Sơn, Đền Voi Phục, Đền Quán Thánh, Đình Bát Tràng, Đình Kim Liên, Chùa Tĩnh Lâu, Phủ Tây Hồ, Đền Bạch Mã,...\r\n\r\nHà Nội cũng có nhiều Nhà thờ: Nhà thờ Lớn Hà Nội, Nhà thờ Cửa Bắc, Nhà thờ Hàm Long, Nhà thờ Giáp Bát, Nhà thờ Tin lành (Ngõ Trạm), Nhà thờ Hồi Giáo Thánh đường Jamia Al Noor (Thánh đường Ánh Sáng), Thánh thất Cao Đài Thủ Đô.', 'hanoi.jpg'),
(2, 'Đà Nẵng', 'Đà Nẵng là một thành phố có nhiều tiềm năng để phát triển du lịch, là trung tâm du lịch lớn hàng đầu của Việt Nam. Phía bắc thành phố được bao bọc bởi núi cao với đèo Hải Vân được mệnh danh là \"Thiên hạ đệ nhất hùng quan\". Phía tây là Khu nghỉ dưỡng Sun World Bà Nà Hills nằm ở độ cao trên 1.400m với hệ thống 8 tuyến cáp treo có tổng công suất phục vụ 9.500 khách/giờ, tuyến cáp treo Thác Tóc Tiên - Indochine đạt bốn kỷ lục thế giới (Cáp treo một dây dài nhất thế giới: 5,771.61m; Độ chênh giữa ga đi và ga đến cao nhất thế giới: 1,368.93m; Chiều dài một sợi cáp không nối dài nhất thế giới: 11,587m; Độ nặng cuộn cáp nặng nhất thế giới: 141.24 tấn) cùng khu vui chơi giải trí trong nhà Fantasy Park lớn nhất Đông Nam Á và khu làng Pháp lớn nhất Việt Nam, mỗi ngày khu nghỉ dưỡng Sun World Bà Nà Hills phục vụ hơn 30.000 lượt khách, các ngày cao điểm lên tới hơn 40.000 lượt khách.\r\n\r\nPhía đông bắc là bán đảo Sơn Trà với 400 ha rừng nguyên sinh gồm nhiều động thực vật phong phú. Phía đông nam là danh thắng Ngũ Hành Sơn. Trên địa bàn thành phố còn có một hệ thống các đình, chùa, miếu theo kiến trúc Á Đông, các nhà thờ theo kiến trúc phương Tây như Nhà thờ Con Gà,...các bảo tàng mà tiêu biểu nhất là Bảo tàng Nghệ thuật Điêu khắc Chăm. Đây là bảo tàng trưng bày hiện vật Chăm quy mô nhất ở Việt Nam. Ngoài ra thành phố còn được bao bọc bởi 3 Di sản Văn hóa Thế giới: Huế, Hội An, Mỹ Sơn. Xa hơn một chút nữa là Vườn Quốc gia Bạch Mã, và Di sản Thiên nhiên Thế giới Vườn Quốc gia Phong Nha - Kẻ Bàng. Vì thế Đà Nẵng được xem là điểm trung chuyển quan trọng trên Con đường Di sản miền Trung.', 'danang.jpg'),
(3, 'Quảng Ninh', 'Vịnh Hạ Long: một trong bảy kỳ quan thiên nhiên thế giới (N7W), di sản thiên nhiên thế giới, có diện tích 1.553 km² với 1969 đảo. Trong đó khu di sản thế giới được UNESCO công nhận có diện tích trên 434 km² với 788 đảo, có giá trị đặc biệt về văn hoá, thẩm mĩ, địa chất, sinh học và kinh tế. Trên vịnh có nhiều đảo đất, hang động, bãi tắm, cảnh quan đẹp thuận lợi cho phát triển nhiều điểm, nhiều hình thức du lịch hấp dẫn. Vịnh Hạ Long cùng với đảo Cát Bà là khu du lịch trọng điểm quốc gia, động lực phát triển vùng du lịch Bắc Bộ.\r\nVịnh Bái Tử Long nằm liền với vịnh Hạ Long ở phía bắc với nhiều đảo đá trải dài ven biển. Một vẻ đẹp hoang sơ cùng với các bãi tắm tại các đảo như Quan Lạn, Minh Châu, Ngọc Vừng...(Vân Đồn). Phục vụ các du khách thích khám phá tận hưởng vẻ đẹp nguyên sơ của thiên nhiên.\r\nHàng chục bãi tắm bãi tắm đẹp hiện đại như Trà Cổ (Móng Cái), Bãi Cháy, đảo Tuần Châu được cải tạo, nâng cấp với nhiều loại hình dịch vụ phục vụ đa dạng các nhu cầu của du khách.\r\nĐảo Cô Tô (phía đông bắc Quảng Ninh). Các bãi tắm đẹp như Hồng Vàn, Bắc Vàn, Vàn Chảy, đảo Cô Tô Con...', 'quangninh.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `gioithieu`
--

CREATE TABLE `gioithieu` (
  `id` int(10) NOT NULL,
  `descriptions` text NOT NULL,
  `address` text NOT NULL,
  `contact` text NOT NULL,
  `rule` text CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `gioithieu`
--

INSERT INTO `gioithieu` (`id`, `descriptions`, `address`, `contact`, `rule`) VALUES
(1, 'VietTravel là dự án du lịch được thiết kế nhằm phục vụ nhu cầu tham quan cũng như khám phá từng vùng miền Việt Nam. Không chỉ đưa ra những chuyến du lịch sẵn có với nhiều ưu đãi hấp dẫn mà còn dành ri', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6385.839489800844!2d105.8003788370853!3d21.007320721663103!2m3!1f0!2f0!3f0!3m2!1i1020!2i768!4f13.1!3m3!1m2!1s0x3135aca174cdefa3%3A0x13d034e92518a7', 'SDT: 0928230347\r\nGmail: VietTravel@gmail.com\r\nFacebook: https://wwww.facebook.com/viettravel', '\r\n- Không mang chất cấm hay văn hóa phẩm không được lưu hành  khi tham gia tour\r\n- Phản ánh ngày cho công ty nếu có bất kỳ vấn đề gì với HDV, tài xế cũng như dịch vụ trên tour mà không giống như cam kết\r\n- Thanh toán chi phí đúng thời hạn và quy định của VietTravel\r\n- Tự bảo quản tài sản cá nhân khi mang theo tour\r\n\r\n');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion`
--

CREATE TABLE `promotion` (
  `mauudai` int(11) NOT NULL,
  `sale` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `mota` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `anh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `promotion`
--

INSERT INTO `promotion` (`mauudai`, `sale`, `mota`, `anh`) VALUES
(1, '20%', 'Tour đang trong thời gian khuyến mãi và được giảm giá 10% ', 'sale10.jpg'),
(2, '30%', 'Tour đang trong thời gian khuyến mãi và được giảm giá 30% ', 'sale30.jpg'),
(3, '50%', 'Tour đang trong thời gian khuyến mãi và được giảm giá lên tới 50% ', 'sale50.jpg'),
(4, '0', '0', '0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `id` int(11) NOT NULL,
  `categoryid` int(11) NOT NULL,
  `promotionid` int(11) NOT NULL,
  `diemdi` text NOT NULL,
  `diemden` text NOT NULL,
  `timedi` varchar(20) NOT NULL,
  `timeve` varchar(20) NOT NULL,
  `descriptions` text NOT NULL,
  `images` text NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`id`, `categoryid`, `promotionid`, `diemdi`, `diemden`, `timedi`, `timeve`, `descriptions`, `images`, `price`) VALUES
(1, 1, 1, 'Thái Bình', 'Hà Nội', '2020-07-08', '2020-12-01', 'Muốn tìm hiểu về cuộc sống, văn hóa và con người Tràng An thì bạn đừng bỏ qua phố cổ – một trong những địa điểm du lịch ở Hà Nội đầy thú vị và hấp dẫn với du khách. Phố cổ Hà Nội nằm ở phía Tây và phía Bắc của Hồ Hoàn Kiếm, là nơi tập trung đông dân cư sinh sống có 36 phố phường. Mỗi con phố ở đây chủ yếu tập trung bán một loại mặt hàng nhất định. \r\nLang thang ở khu phố và thưởng thức ẩm thực phố cổ như phở Bát Đàn, chả cá Lã Vọng, bún chả hàng Mành, mì vằn thắn Đinh Liệt, bún ốc nguội Ô Quan Chưởng,…sẽ khiến chuyến đi của bạn đáng nhớ hơn rất nhiều!', 'thaibinhhanoi.jpg', 1000000),
(2, 1, 1, 'Nam Định', 'Hà Nội', '2020-11-04', '2020-11-06', 'Hồ Gươm hay hồ Hoàn Kiếm là một trong những nơi nên đến ở Hà Nội khi du lịch thủ đô. Nằm ở giữa trung tâm, Hồ Gươm được ví như trái tim của thành phố ngàn năm tuổi này.. Mặt hồ như tấm gương lớn soi bóng những cây cổ thụ, những rặng liễu thướt tha tóc rủ, những mái đền, chùa cổ kính, tháp cũ rêu phong, các toà nhà mới cao tầng vươn lên trời xanh.\r\nMột trải nghiệm thú vị dành cho khách du lịch là đi bộ một vòng hồ, bạn sẽ được thấy một Hà Nội cổ kính nhưng vẫn đầy hiện đại hiện lên thật rõ ràng. Bên cạnh hồ là những công trình kiến trúc như tháp Bút, đài Nghiên, cầu Thê Húc dẫn vào đền Ngọc Sơn, đền vua Lê Thái Tổ, tháp Hoà Phong,…', 'namdinhhanoi.jpg', 500000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) CHARACTER SET utf8 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 NOT NULL,
  `phone` int(12) NOT NULL,
  `address` text CHARACTER SET utf8 NOT NULL,
  `email` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `phone`, `address`, `email`) VALUES
(1, 'admin', 'admin', 0, '', ''),
(2, 'user', 'user', 0, '', '');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `banner`
--
ALTER TABLE `banner`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `gioithieu`
--
ALTER TABLE `gioithieu`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`mauudai`);

--
-- Chỉ mục cho bảng `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_macategory` (`categoryid`),
  ADD KEY `fk_mauudai` (`promotionid`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `banner`
--
ALTER TABLE `banner`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `gioithieu`
--
ALTER TABLE `gioithieu`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `promotion`
--
ALTER TABLE `promotion`
  MODIFY `mauudai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tour`
--
ALTER TABLE `tour`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tour`
--
ALTER TABLE `tour`
  ADD CONSTRAINT `fk_macategory` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mauudai` FOREIGN KEY (`promotionid`) REFERENCES `promotion` (`mauudai`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
