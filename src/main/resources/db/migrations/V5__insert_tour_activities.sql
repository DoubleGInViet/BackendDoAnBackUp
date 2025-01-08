INSERT INTO tour (name, address, description, attraction_id, active) VALUES
('Buổi biểu diễn múa rối nước Thăng Long', 'Thang Long Water Puppet Theatre, Đinh Tiên Hoàng, Ly Thai To, Hoàn Kiếm, Hanoi', 'Trải nghiệm này sẽ mang đến cho bạn cơ hội khám phá nghệ thuật múa rối nước xa xưa ở Hà Nội. Bạn sẽ ghé thăm nhà hát múa rối nước Thăng Long mang tính biểu tượng, nơi bạn có thể xem những nghệ sĩ múa rối điêu luyện trình diễn. Vé bao gồm buổi biểu diễn múa rối kéo dài 50 phút sử dụng các nhạc cụ truyền thống và những câu truyện dân gian Việt Nam.', 1, 1),
('Tour đi bộ tham quan thành phố có hướng dẫn viên', 'Hanoi', 'Trong tour riêng này, bạn sẽ được đi bộ và khám phá một số điểm tham quan nổi bật nhất ở Hà Nội. Trước tiên, bạn sẽ cùng hướng dẫn viên ghé Hồ Hoàn Kiếm, nơi được đông đảo người dân địa phương xem là trung tâm thủ đô. Từ đây, bạn sẽ tham quan các địa điểm nổi bật khác như Lăng Chủ tịch Hồ Chí Minh, Nhà sàn Bác Hồ, Phủ Chủ tịch, Bảo tàng Hồ Chí Minh, Chùa Một Cột, cùng nhiều điểm tham quan nổi tiếng khác. Ngoài ra, bạn sẽ được khám phá hai địa danh được bảo tồn kỹ lưỡng và mang tính biểu tượng là Văn Miếu và Đại học Quốc gia. Xuyên suốt tour, hướng dẫn viên sẽ cung cấp nhiều thông tin về Phố cổ, lịch sử, văn hóa và kiến trúc ở Hà Nội.', 1, 1),
('Vé Sun World Ba Na Hills', 'Da Nang Downtown, 2/9 Street, Hai Chau, Da Nang', 'Với chiếc vé này, bạn sẽ có thể vào khu phức hợp nghỉ dưỡng và vui chơi giải trí Sun World Ba Na Hills. Bạn sẽ có thể trải nghiệm một trong những chuyến đi cáp treo dài nhất thế giới, mang đến cho bạn tầm nhìn bao quát từ đỉnh núi Chua. Trong số các điểm nổi bật của khu phức hợp này còn có bản sao của một ngôi làng Pháp thời cổ điển, tượng Phật cao 27 mét cũng như các trò giải trí như tàu lượn siêu tốc Alpine Coaster, Công viên Fantasy và Linh Phong Thiền Tự. Bạn cũng có thể ghé thăm Cầu Vàng nổi tiếng và Hầm rượu Debay lịch sử.', 5, 1);

-- Thêm dữ liệu vào bảng tour_schedule
INSERT INTO tour_schedule (start_time, end_time, tour_id) VALUES
('08:00:00', '18:00:00', 1),
('09:00:00', '19:00:00', 2),
('07:30:00', '17:30:00', 3);

-- Thêm dữ liệu vào bảng tour_image
INSERT INTO tour_image (image_url, tour_id) VALUES
('https://q-xx.bstatic.com/xdata/images/xphoto/max1200/153554304.jpg?k=c4f7d09fa35799ed21178297bd8c6c0bf349d0002eefaec21aff476e3b8012f3&o=', 1),
('https://r-xx.bstatic.com/xdata/images/xphoto/max1200/153554370.jpg?k=60f836cd315f27d044b5962e15bc519d726bc0bcf0bead6b3b737cb1933165f5&o=', 1),
('https://q-xx.bstatic.com/xdata/images/xphoto/max1200/153554432.jpg?k=109cb97e143d42661d9be34ced8cacc55152646089a4b2d542cf46e59547af82&o=', 1),
('https://r-xx.bstatic.com/xdata/images/xphoto/max1200/222171388.jpg?k=5e8c66b47d003a3652ad270a965c8cf854b816db44e51c99ba83e785fd3905c0&o=', 2),
('https://q-xx.bstatic.com/xdata/images/xphoto/max1200/222171393.jpg?k=5596c5ed79c2225bb8a6868456c37a5cbfa2306b90298d0f397e831bf6496334&o=', 2),
('https://r-xx.bstatic.com/xdata/images/xphoto/max1200/222171401.jpg?k=eedb91386e8df29830c0783bd55c6cbcefde29a3b8e38e724a94961b42a9cc6f&o=', 2),
('https://r-xx.bstatic.com/xdata/images/xphoto/max1200/222171388.jpg?k=5e8c66b47d003a3652ad270a965c8cf854b816db44e51c99ba83e785fd3905c0&o=', 3),
('https://q-xx.bstatic.com/xdata/images/xphoto/max1200/222171393.jpg?k=5596c5ed79c2225bb8a6868456c37a5cbfa2306b90298d0f397e831bf6496334&o=', 3),
('https://r-xx.bstatic.com/xdata/images/xphoto/max1200/222171401.jpg?k=eedb91386e8df29830c0783bd55c6cbcefde29a3b8e38e724a94961b42a9cc6f&o=', 3);

-- Thêm dữ liệu vào bảng ticket_class
INSERT INTO ticket_class (name, price, max_amount, description, tour_id, active) VALUES
('Deluxe Ticket', 500000, 100, 'Deluxe seat at Thang Long Water Puppet', 1, 1),
('Standard Ticket', 250000, 50, 'Standard seat at Thang Long Water Puppet', 1, 1),
('VIP Ticket', 1000000, 20, 'VIP seat at Thang Long Water Puppet', 2, 1),
('VIP Ticket', 1000000, 20, 'VIP seat at Thang Long Water Puppet', 3, 1),
('Private Walking Tour of Hanoi City', 1000000, 20, 'Trải nghiệm VIP cho khách hàng', 2, 1);

-- Thêm dữ liệu vào bảng daily_ticket_availability
INSERT INTO daily_ticket_availability (happen_date, tour_schedule_id, ticket_class_id, available_ticket) VALUES
('2024-12-27', 1, 1, 50),  -- Tour Sài Gòn, vé người lớn
('2024-12-27',1, 2, 25),  -- Tour Sài Gòn, vé trẻ em
('2024-12-27',2, 3, 15);  -- Tour Hà Nội, vé VIP

-- Thêm dữ liệu vào bảng profit
INSERT INTO profit (name) VALUES
('Lợi nhuận bán vé'),
('Lợi nhuận dịch vụ bổ sung');

-- Thêm dữ liệu vào bảng ticket_profit
INSERT INTO ticket_profit (profit_id, ticket_class_id) VALUES
(1, 1),  -- Lợi nhuận từ vé người lớn của Tour Sài Gòn
(1, 2),  -- Lợi nhuận từ vé trẻ em của Tour Sài Gòn
(2, 3);  -- Lợi nhuận từ vé VIP của Tour Hà Nội

--
INSERT INTO `doan`.`review_tour` (`id`, `rating`, `comment`, `review_date`, `tour_id`, `user_id`) VALUES
('1', '3', 'Nice', '2025-01-08', '1', '1'),
('2', '4', 'Niceeeee', '2025-01-08', '1', '4'),
('3', '5', 'Pretty', '2025-01-08', '2', '2');