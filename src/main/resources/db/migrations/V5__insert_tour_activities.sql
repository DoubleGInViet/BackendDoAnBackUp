INSERT INTO tour (name, address, description, attraction_id) VALUES
('Tour Sài Gòn', 'Quận 1, TP.HCM', 'Khám phá vẻ đẹp Sài Gòn về đêm', 1),
('Tour Hà Nội', 'Hoàn Kiếm, Hà Nội', 'Khám phá phố cổ Hà Nội', 2),
('Tour Đà Nẵng', 'Sơn Trà, Đà Nẵng', 'Khám phá bãi biển Mỹ Khê', 3);

-- Thêm dữ liệu vào bảng tour_schedule
INSERT INTO tour_schedule (happen_date, start_time, end_time, tour_id) VALUES
('2024-12-25', '08:00:00', '18:00:00', 1),
('2024-12-26', '09:00:00', '19:00:00', 2),
('2024-12-27', '07:30:00', '17:30:00', 3);

-- Thêm dữ liệu vào bảng tour_image
INSERT INTO tour_image (image_url, tour_id) VALUES
('https://example.com/saigon1.jpg', 1),
('https://example.com/hanoi1.jpg', 2),
('https://example.com/danang1.jpg', 3);

-- Thêm dữ liệu vào bảng ticket_class
INSERT INTO ticket_class (name, price, max_amount, description, tour_id) VALUES
('Vé người lớn', 500000, 100, 'Vé dành cho người trên 18 tuổi', 1),
('Vé trẻ em', 250000, 50, 'Vé dành cho trẻ em từ 6-17 tuổi', 1),
('Vé VIP', 1000000, 20, 'Trải nghiệm VIP cho khách hàng', 2);

-- Thêm dữ liệu vào bảng daily_ticket_availability
INSERT INTO daily_ticket_availability (tour_schedule_id, ticket_class_id, available_ticket) VALUES
(1, 1, 50),  -- Tour Sài Gòn, vé người lớn
(1, 2, 25),  -- Tour Sài Gòn, vé trẻ em
(2, 3, 15);  -- Tour Hà Nội, vé VIP

-- Thêm dữ liệu vào bảng profit
INSERT INTO profit (name) VALUES
('Lợi nhuận bán vé'),
('Lợi nhuận dịch vụ bổ sung');

-- Thêm dữ liệu vào bảng ticket_profit
INSERT INTO ticket_profit (profit_id, ticket_class_id) VALUES
(1, 1),  -- Lợi nhuận từ vé người lớn của Tour Sài Gòn
(1, 2),  -- Lợi nhuận từ vé trẻ em của Tour Sài Gòn
(2, 3);  -- Lợi nhuận từ vé VIP của Tour Hà Nội