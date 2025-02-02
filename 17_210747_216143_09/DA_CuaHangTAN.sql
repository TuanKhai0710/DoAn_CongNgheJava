create database DA_QL_CHTAN
USE [DA_QL_CHTAN]
GO
/****** Object:  Table [dbo].[CHITIETHOADON]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHITIETHOADON](
	[ID] [int] NULL,
	[MASP] [char](10) NULL,
	[SOLUONG] [int] NULL,
	[GIATIEN] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHITIETPHIEUNHAP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHITIETPHIEUNHAP](
	[MAPNH] [char](10) NOT NULL,
	[MASP] [char](10) NOT NULL,
	[SOLUONG] [int] NULL,
	[DONGIA] [int] NULL,
 CONSTRAINT [PK_MAPNH_MASP] PRIMARY KEY CLUSTERED 
(
	[MAPNH] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTKHUYENMAI]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTKHUYENMAI](
	[MAKM] [char](10) NULL,
	[MASP] [char](10) NULL,
	[HESOKM] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[MAHD] [char](10) NOT NULL,
	[NGAYMUA] [nvarchar](12) NULL,
	[TONGTIEN] [int] NULL,
	[TRANGTHAI] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MAHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[MAKH] [char](10) NOT NULL,
	[TENKH] [nvarchar](40) NULL,
	[DIACHI] [nvarchar](50) NULL,
	[SDT] [char](13) NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHO]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHO](
	[MAKHO] [char](10) NULL,
	[MASP] [char](10) NULL,
	[SOLUONG] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHUYENMAI]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHUYENMAI](
	[MAKM] [char](10) NOT NULL,
	[NGAYBD] [date] NULL,
	[NGAYKT] [date] NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAISANPHAM]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAISANPHAM](
	[MALOAISP] [char](10) NOT NULL,
	[LOAISP] [nvarchar](30) NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MALOAISP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHACUNGCAP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHACUNGCAP](
	[MANCC] [char](10) NOT NULL,
	[TENNCC] [nvarchar](50) NULL,
	[SDT] [char](15) NULL,
	[DIACHI] [nvarchar](50) NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MANCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MANV] [char](10) NOT NULL,
	[TENNV] [nvarchar](40) NULL,
	[CHUCVU] [nvarchar](20) NULL,
	[SDT] [char](13) NULL,
	[NGAYVAOLAM] [nvarchar](11) NULL,
	[GIOITINH] [char](5) NULL,
	[LUONG] [int] NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHIEUNHAPHANG]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHIEUNHAPHANG](
	[MAPNH] [char](10) NOT NULL,
	[MANV] [char](10) NULL,
	[MANCC] [char](10) NULL,
	[NGAYNHAP] [date] NULL,
	[TONGTIEN] [int] NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAPNH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[MASP] [char](10) NOT NULL,
	[MALOAISP] [char](10) NULL,
	[TENSP] [nvarchar](40) NULL,
	[GIANHAP] [int] NULL,
	[GIASP] [int] NULL,
	[HINHANH] [varchar](30) NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CHITIETHOADON] ([ID], [MASP], [SOLUONG], [GIATIEN]) VALUES (0, N'SP001     ', 3, 90000)
INSERT [dbo].[CHITIETHOADON] ([ID], [MASP], [SOLUONG], [GIATIEN]) VALUES (0, N'SP006     ', 4, 40000)
GO
INSERT [dbo].[HOADON] ([MAHD], [NGAYMUA], [TONGTIEN], [TRANGTHAI]) VALUES (N'HD001     ', N'2024-04-20', 45000, N'Đã thanh toán')
INSERT [dbo].[HOADON] ([MAHD], [NGAYMUA], [TONGTIEN], [TRANGTHAI]) VALUES (N'HD002     ', N'2024-04-20', 45000, N'Đã thanh toán')
INSERT [dbo].[HOADON] ([MAHD], [NGAYMUA], [TONGTIEN], [TRANGTHAI]) VALUES (N'HD003     ', N'2024-04-20', 45000, N'Đã thanh toán')
INSERT [dbo].[HOADON] ([MAHD], [NGAYMUA], [TONGTIEN], [TRANGTHAI]) VALUES (N'HD004     ', N'2024-04-20', 45000, N'Đã thanh toán')
GO
INSERT [dbo].[KHACHHANG] ([MAKH], [TENKH], [DIACHI], [SDT], [TRANGTHAI]) VALUES (N'KH001     ', N'Trần Thái Thanh', N'Bình Thuận', N'0396500615   ', 0)
INSERT [dbo].[KHACHHANG] ([MAKH], [TENKH], [DIACHI], [SDT], [TRANGTHAI]) VALUES (N'KH002     ', N'Nguyễn Kim Tiền', N'Đà Nẵng', N'0363497318   ', 0)
INSERT [dbo].[KHACHHANG] ([MAKH], [TENKH], [DIACHI], [SDT], [TRANGTHAI]) VALUES (N'KH003     ', N'Nguyễn Kim Ngọc Thích', N'Bình Dương', N'0983764311   ', 0)
INSERT [dbo].[KHACHHANG] ([MAKH], [TENKH], [DIACHI], [SDT], [TRANGTHAI]) VALUES (N'KH004     ', N'Phạm Trần Minh Thư', N'Bình Thuận', N'0378021742   ', 0)
INSERT [dbo].[KHACHHANG] ([MAKH], [TENKH], [DIACHI], [SDT], [TRANGTHAI]) VALUES (N'KH005     ', N'Trần Thanh Phát', N'Nam Định', N'0393690772   ', 0)
GO
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP001     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP002     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP003     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP004     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP005     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP006     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP007     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP008     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP009     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP010     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP011     ', 50)
INSERT [dbo].[KHO] ([MAKHO], [MASP], [SOLUONG]) VALUES (N'K001      ', N'SP012     ', 50)
GO
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI001   ', N'Sandwich', 0)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI002   ', N'Hamburger', 0)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI003   ', N'Pizza', 0)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI004   ', N'Gà rán', 0)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI005   ', N'Nước giải khát', 0)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI006   ', N'Bánh mì', 0)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI007   ', N'mmm', 1)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI008   ', N'mmm', 1)
INSERT [dbo].[LOAISANPHAM] ([MALOAISP], [LOAISP], [TRANGTHAI]) VALUES (N'LOAI009   ', N'ttt', 1)
GO
INSERT [dbo].[NHACUNGCAP] ([MANCC], [TENNCC], [SDT], [DIACHI], [TRANGTHAI]) VALUES (N'NCC001    ', N'Công ty cổ phần thực phẩm Masan', N'0987889998     ', N'123/55 Đ. Lý Thường Kiệt, P10, Quận Tân Bình', 0)
INSERT [dbo].[NHACUNGCAP] ([MANCC], [TENNCC], [SDT], [DIACHI], [TRANGTHAI]) VALUES (N'NCC002    ', N'Chợ đầu mối Hóc Môn', N'0937659898     ', N'14/7A Đ. Nguyễn Thị Sóc, Hóc Môn, TP HCM', 0)
INSERT [dbo].[NHACUNGCAP] ([MANCC], [TENNCC], [SDT], [DIACHI], [TRANGTHAI]) VALUES (N'NCC003    ', N'Đại lý nước giải khát Tân Bình', N'0934568799     ', N'678/54 Đ. Lạc Long Quân, P10, Quận Tân Bình', 0)
INSERT [dbo].[NHACUNGCAP] ([MANCC], [TENNCC], [SDT], [DIACHI], [TRANGTHAI]) VALUES (N'NCC004    ', N'Chợ Trần Văn Quang', N'0982435672     ', N'798/54 Đ. Trần Văn Quang, P10, Quận Tân Bình', 0)
INSERT [dbo].[NHACUNGCAP] ([MANCC], [TENNCC], [SDT], [DIACHI], [TRANGTHAI]) VALUES (N'NCC005    ', N'Lò bánh mì Ngọc Quân', N'0963771183     ', N'66 Đ. Ni Sư Huỳnh Liên, P10, Quận Tân Bình', 0)
GO
INSERT [dbo].[NHANVIEN] ([MANV], [TENNV], [CHUCVU], [SDT], [NGAYVAOLAM], [GIOITINH], [LUONG], [TRANGTHAI]) VALUES (N'NV001     ', N'Nguyễn Đức Công Tài', N'Quản lý', N'0931623791   ', N'2022-02-15', N'Nam  ', 5000000, 0)
INSERT [dbo].[NHANVIEN] ([MANV], [TENNV], [CHUCVU], [SDT], [NGAYVAOLAM], [GIOITINH], [LUONG], [TRANGTHAI]) VALUES (N'NV002     ', N'Nguyễn Bảo Trân', N'Phục vụ', N'0373692186   ', N'2023-06-10', N'N?   ', 3000000, 0)
INSERT [dbo].[NHANVIEN] ([MANV], [TENNV], [CHUCVU], [SDT], [NGAYVAOLAM], [GIOITINH], [LUONG], [TRANGTHAI]) VALUES (N'NV003     ', N'Trần Chí Công', N'Phục vụ', N'0325192358   ', N'2023-07-25', N'Nam  ', 3000000, 0)
INSERT [dbo].[NHANVIEN] ([MANV], [TENNV], [CHUCVU], [SDT], [NGAYVAOLAM], [GIOITINH], [LUONG], [TRANGTHAI]) VALUES (N'NV004     ', N'Võ Văn Quang', N'Phục vụ', N'0992346321   ', N'2024-01-02', N'Nam  ', 2500000, 0)
INSERT [dbo].[NHANVIEN] ([MANV], [TENNV], [CHUCVU], [SDT], [NGAYVAOLAM], [GIOITINH], [LUONG], [TRANGTHAI]) VALUES (N'NV005     ', N'Võ Hạ Trân', N'Phục vụ', N'0391379825   ', N'2024-02-20', N'N?   ', 2500000, 0)
GO
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP001     ', N'LOAI001   ', N'Bánh Sandwich thịt gà', 20000, 30000, N'SP001\SP1.png', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP002     ', N'LOAI003   ', N'Pizza hải sản', 35000, 50000, N'SP2.png', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP003     ', N'LOAI004   ', N'Đùi gà sốt cay', 20000, 35000, N'SP003\SP3.png', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP004     ', N'LOAI004   ', N'Cánh gà sốt phô mai', 25000, 35000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP005     ', N'LOAI002   ', N'Hamburger thịt bò phô mai', 20000, 40000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP006     ', N'LOAI005   ', N'Coca', 7000, 10000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP007     ', N'LOAI005   ', N'Trà sữa', 15000, 25000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP008     ', N'LOAI003   ', N'Pizza xúc xích phô mai', 25000, 40000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP009     ', N'LOAI006   ', N'Bánh mì chả', 10000, 20000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP010     ', N'LOAI006   ', N'Bánh mì xíu mại', 12000, 25000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP011     ', N'LOAI005   ', N'Nước Cam', 10000, 20000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP012     ', N'LOAI005   ', N'Trà tắc', 7000, 10000, N'', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP013     ', N'LOAI005   ', N'Trà sữa', 7500, 15000, N'', 1)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP014     ', N'LOAI001   ', N'Sanwich Ca hoi', 20000, 40000, N'SP003\SP3.png', 0)
INSERT [dbo].[SANPHAM] ([MASP], [MALOAISP], [TENSP], [GIANHAP], [GIASP], [HINHANH], [TRANGTHAI]) VALUES (N'SP015     ', N'LOAI001   ', N'Sanwich', 200, 300, N'SP002\SP2.png', 0)
GO
ALTER TABLE [dbo].[CHITIETHOADON]  WITH CHECK ADD  CONSTRAINT [FK_CTHD_MASP] FOREIGN KEY([MASP])
REFERENCES [dbo].[SANPHAM] ([MASP])
GO
ALTER TABLE [dbo].[CHITIETHOADON] CHECK CONSTRAINT [FK_CTHD_MASP]
GO
ALTER TABLE [dbo].[CHITIETPHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK_CTPNH_MAPNH] FOREIGN KEY([MAPNH])
REFERENCES [dbo].[PHIEUNHAPHANG] ([MAPNH])
GO
ALTER TABLE [dbo].[CHITIETPHIEUNHAP] CHECK CONSTRAINT [FK_CTPNH_MAPNH]
GO
ALTER TABLE [dbo].[CHITIETPHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK_CTPNH_MASP] FOREIGN KEY([MASP])
REFERENCES [dbo].[SANPHAM] ([MASP])
GO
ALTER TABLE [dbo].[CHITIETPHIEUNHAP] CHECK CONSTRAINT [FK_CTPNH_MASP]
GO
ALTER TABLE [dbo].[CTKHUYENMAI]  WITH CHECK ADD  CONSTRAINT [FK_CTKM_MAKM] FOREIGN KEY([MAKM])
REFERENCES [dbo].[KHUYENMAI] ([MAKM])
GO
ALTER TABLE [dbo].[CTKHUYENMAI] CHECK CONSTRAINT [FK_CTKM_MAKM]
GO
ALTER TABLE [dbo].[CTKHUYENMAI]  WITH CHECK ADD  CONSTRAINT [FK_CTKM_MASP] FOREIGN KEY([MASP])
REFERENCES [dbo].[SANPHAM] ([MASP])
GO
ALTER TABLE [dbo].[CTKHUYENMAI] CHECK CONSTRAINT [FK_CTKM_MASP]
GO
ALTER TABLE [dbo].[KHO]  WITH CHECK ADD  CONSTRAINT [FK_KH0_SP] FOREIGN KEY([MASP])
REFERENCES [dbo].[SANPHAM] ([MASP])
GO
ALTER TABLE [dbo].[KHO] CHECK CONSTRAINT [FK_KH0_SP]
GO
ALTER TABLE [dbo].[PHIEUNHAPHANG]  WITH CHECK ADD  CONSTRAINT [FK_PNH_MANCC] FOREIGN KEY([MANCC])
REFERENCES [dbo].[NHACUNGCAP] ([MANCC])
GO
ALTER TABLE [dbo].[PHIEUNHAPHANG] CHECK CONSTRAINT [FK_PNH_MANCC]
GO
ALTER TABLE [dbo].[PHIEUNHAPHANG]  WITH CHECK ADD  CONSTRAINT [FK_PNH_MANV] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[PHIEUNHAPHANG] CHECK CONSTRAINT [FK_PNH_MANV]
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD  CONSTRAINT [FK_SANPHAM_MALOAISP] FOREIGN KEY([MALOAISP])
REFERENCES [dbo].[LOAISANPHAM] ([MALOAISP])
GO
ALTER TABLE [dbo].[SANPHAM] CHECK CONSTRAINT [FK_SANPHAM_MALOAISP]
GO
/****** Object:  StoredProcedure [dbo].[DanhSachCTHD]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhSachCTHD]
as
	select * from CHITIETHOADON
--hoa don
GO
/****** Object:  StoredProcedure [dbo].[DanhSachHoaDon]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhSachHoaDon]
as
	 select * from HOADON where TRANGTHAI = 0
--Tìm Kiếm Nhân Viên
GO
/****** Object:  StoredProcedure [dbo].[DanhSachKhanhHang]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DanhSachKhanhHang]
AS
	select * from KHACHHANG where TRANGTHAI = 0;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachKhanhHang_TheoMaKH]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachKhanhHang_TheoMaKH](@MAKH char(10))
as
	select * from KHACHHANG where TRANGTHAI = 0 and MAKH = @MAKH;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachKhanhHang_TheoTenKH]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachKhanhHang_TheoTenKH](@giatri nvarchar(40))
as
	select * from KHACHHANG where TRANGTHAI = 0 and TENKH like '%' + @giatri + '%'
GO
/****** Object:  StoredProcedure [dbo].[DanhsachLoaiSanPham]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[DanhsachLoaiSanPham]
AS
    SELECT * FROM LOAISANPHAM WHERE TRANGTHAI = 0;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachLoaiSanPham_TheoLoaiSP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachLoaiSanPham_TheoLoaiSP](@giatri nvarchar(30))
as
	select * from LOAISANPHAM where TRANGTHAI = 0 and LOAISP like '%' + @giatri + '%' 
GO
/****** Object:  StoredProcedure [dbo].[DanhsachLoaiSanPham_TheoMaLoaiSP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachLoaiSanPham_TheoMaLoaiSP](@MALOAISP char(10))
as
	select * from LOAISANPHAM where TRANGTHAI = 0 and MALOAISP = @MALOAISP;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachNhaCungCap]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[DanhsachNhaCungCap]
AS
    SELECT * FROM NHACUNGCAP WHERE TRANGTHAI = 0;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachNhaCungCap_TheoMaNCC]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachNhaCungCap_TheoMaNCC](@MANCC char(10))
as
	select * from NHACUNGCAP where TRANGTHAI = 0 and MANCC = @MANCC;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachNhaCungCap_TheoTenNCC]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachNhaCungCap_TheoTenNCC](@giatri nvarchar(50))
as
	select * from NHACUNGCAP where TRANGTHAI = 0 and TENNCC like '%' + @giatri + '%'
GO
/****** Object:  StoredProcedure [dbo].[DanhSachNhanVien]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DanhSachNhanVien]
AS
	select * from NHANVIEN where TRANGTHAI = 0;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachNhanVien_TheoMaNV]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachNhanVien_TheoMaNV](@MANV char(10))
as
	select * from NHANVIEN where TRANGTHAI = 0 and MANV = @MANV;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachNhanVien_TheoTenNV]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachNhanVien_TheoTenNV](@giatri nvarchar(40))
as
	select * from NHANVIEN where TRANGTHAI = 0 and TENNV like '%' + @giatri + '%'
GO
/****** Object:  StoredProcedure [dbo].[DanhsachSanPham]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[DanhsachSanPham]
AS
    SELECT * FROM SANPHAM WHERE TRANGTHAI = 0;
exec DanhsachSanPham
GO
/****** Object:  StoredProcedure [dbo].[DanhsachSanPham_TheoMaSP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[DanhsachSanPham_TheoMaSP](@MASP char(10))
as
	select * from SANPHAM where TRANGTHAI = 0 and MASP = @MASP;
GO
/****** Object:  StoredProcedure [dbo].[DanhsachSanPham_TheoTenSP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[DanhsachSanPham_TheoTenSP](@giatri nvarchar(40))
as
	select * from SANPHAM where TRANGTHAI = 0 and TENSP like '%' + @giatri + '%'
GO
/****** Object:  StoredProcedure [dbo].[Delete_HoaDon]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Delete_HoaDon](@MAHD char(10))
as
	delete from HOADON where MAHD = @MAHD
GO
/****** Object:  StoredProcedure [dbo].[Delete_KhachHang]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Delete_KhachHang](@MAKH char(10))
as
	UPDATE KHACHHANG
	SET TRANGTHAI = 1
	WHERE MAKH = @MAKH
GO
/****** Object:  StoredProcedure [dbo].[Delete_LoaiSP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Delete_LoaiSP](@MaLoaiSP char(10))
as
	UPDATE LOAISANPHAM
	SET TRANGTHAI = 1
	WHERE MALOAISP = @MaLoaiSP
GO
/****** Object:  StoredProcedure [dbo].[Delete_NhaCungCap]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Delete_NhaCungCap](@MaNCC char(10))
as
	UPDATE NHACUNGCAP
	SET TRANGTHAI = 1
	WHERE MANCC = @MaNCC
GO
/****** Object:  StoredProcedure [dbo].[Delete_NhanVien]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Delete_NhanVien](@MANV char(10))
as
	UPDATE NHANVIEN
	SET TRANGTHAI = 1
	WHERE MANV = @MANV
GO
/****** Object:  StoredProcedure [dbo].[Delete_SanPham]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Delete_SanPham](@MaSP char(10))
as
	UPDATE SANPHAM
	SET TRANGTHAI = 1
	WHERE MASP = @MaSP
GO
/****** Object:  StoredProcedure [dbo].[edit_KhachHang]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[edit_KhachHang](@MAKH char(10), @TENKH nvarchar(30), @DIACHI nvarchar(50), @SDT char(13))
as
	UPDATE KHACHHANG 
	SET TENKH = @TENKH, DIACHI=@DIACHI, SDT=@SDT 
	WHERE MAKH = @MAKH 
GO
/****** Object:  StoredProcedure [dbo].[edit_LoaiSP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[edit_LoaiSP](@MALOAISP char(10), @LOAISP nvarchar(30))
as
	UPDATE LOAISANPHAM SET LOAISP = @LOAISP WHERE MALOAISP = @MALOAISP
GO
/****** Object:  StoredProcedure [dbo].[edit_NhaCungCap]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[edit_NhaCungCap](@MaNCC char(10), @TenNCC nvarchar(50), @Sdt char(10), @DiaChi nvarchar(50))
as
	UPDATE NHACUNGCAP
	SET TENNCC = @TenNCC, SDT = @Sdt, DIACHI = @DiaChi
	WHERE MANCC = @MaNCC
select * from NHACUNGCAP
GO
/****** Object:  StoredProcedure [dbo].[edit_NhanVien]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[edit_NhanVien](@MANV char(10), @TENNV nvarchar(40), @CHUCVU nvarchar(20), @SDT char(13), @NGAYVAOLAM nvarchar(11), @GIOITINH nvarchar(5), @LUONG INT)
as
	UPDATE NHANVIEN 
	SET TENNV = @TENNV, CHUCVU = @CHUCVU, SDT=@SDT, NGAYVAOLAM=@NGAYVAOLAM, GIOITINH=@GIOITINH, LUONG=@LUONG 
	WHERE MANV = @MANV 
GO
/****** Object:  StoredProcedure [dbo].[edit_SanPham]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[edit_SanPham](@MaSP char(10), @MaLoaiSP char(10), @TenSP nvarchar(40), @GiaNhap int, @GiaSP int, @HinhAnh varchar(30))
as
	UPDATE SANPHAM 
	SET MALOAISP = @MaLoaiSP, TENSP = @TenSP, GIANHAP = @GiaNhap, GIASP = @GiaSP, HINHANH = @HinhAnh
	WHERE MASP = @MaSP
GO
/****** Object:  StoredProcedure [dbo].[HDLonNhat]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[HDLonNhat] as
	begin 
		if((select COUNT(*) from HOADON)=0)
			select 'HD000' MAHD
		else 
			select top 1 MAHD from HOADON order by MAHD Desc
	end
exec HDLonNhat
GO
/****** Object:  StoredProcedure [dbo].[insert_HoaDon]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insert_HoaDon](@MAHD char(10), @NGAYMUA nvarchar(12), @TONGTIEN int, @TRANGTHAI nvarchar(30), @TRANGTHAISO int)
as
	insert into HOADON values(@MAHD, @NGAYMUA, @TONGTIEN, @TRANGTHAI, 0)
GO
/****** Object:  StoredProcedure [dbo].[insert_KhachHang]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insert_KhachHang](@MAKH char(10), @TENKH nvarchar(30),@DIACHI nvarchar(50),@SDT char(13))
as
	insert into KHACHHANG values(@MAKH, @TENKH, @DIACHI, @SDT, 0);
GO
/****** Object:  StoredProcedure [dbo].[insert_LoaiSP]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insert_LoaiSP](@MALOAISP char(10), @LOAISP nvarchar(30))
as
	insert into LOAISANPHAM values(@MALOAISP, @LOAISP, 0);
GO
/****** Object:  StoredProcedure [dbo].[insert_NhaCungCap]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insert_NhaCungCap](@MaNCC char(10), @TenNCC nvarchar(50), @SDT char(10), @DiaChi nvarchar(50))
as
insert into NHACUNGCAP values(@MaNCC, @TenNCC, @SDT, @DiaChi,0);
GO
/****** Object:  StoredProcedure [dbo].[insert_NhanVien]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insert_NhanVien](@MANV char(10), @TENNV nvarchar(40),@CHUCVU nvarchar(20),@SDT char(13), @NGAYVAOLAM nvarchar(11), @GIOITINH nvarchar(5),@LUONG int)
as
	insert into NHANVIEN values(@MANV, @TENNV, @CHUCVU, @SDT, @NGAYVAOLAM, @GIOITINH, @LUONG, 0);
GO
/****** Object:  StoredProcedure [dbo].[insert_SanPham]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insert_SanPham](@MaSP char(10), @MaLoaiSP char(10), @TenSP nvarchar(40), @GiaNhap int, @GiaSP int, @HinhAnh varchar(30))
as
	insert into SANPHAM values(@MaSP, @MaLoaiSP, @TenSP, @GiaNhap, @GiaSP, @HinhAnh, 0);
GO
/****** Object:  StoredProcedure [dbo].[KHLonNhat]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[KHLonNhat] as
	begin 
		if((select COUNT(*) from KHACHHANG)=0)
			select 'KH000' MAKH
		else 
			select top 1 MAKH from KHACHHANG order by MAKH Desc
	end
exec KHLonNhat
GO
/****** Object:  StoredProcedure [dbo].[LoaiSPLonNhat]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[LoaiSPLonNhat] as
	begin 
		if((select COUNT(*) from LOAISANPHAM)=0)
			select 'LOAI000' MALOAISP
		else 
			select top 1 MALOAISP from LOAISANPHAM order by MALOAISP Desc
	end
GO
/****** Object:  StoredProcedure [dbo].[NhaCungCapLonNhat]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[NhaCungCapLonNhat] as
	begin
		if((select COUNT(*) from NHACUNGCAP)=0)
			select 'NCC000' MANCC
		else 
			select top 1 MANCC from NHACUNGCAP order by MANCC desc
		end
GO
/****** Object:  StoredProcedure [dbo].[NVLonNhat]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[NVLonNhat] as
	begin 
		if((select COUNT(*) from NHANVIEN)=0)
			select 'NV000' MANV
		else 
			select top 1 MANV from NHANVIEN order by MANV Desc
	end
exec NVLonNhat
GO
/****** Object:  StoredProcedure [dbo].[SanPhamLonNhat]    Script Date: 6/6/2024 9:45:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[SanPhamLonNhat] as
	begin
		if((select COUNT(*) from SANPHAM)=0)
			select 'SP000' MASP
		else 
			select top 1 MASP from SANPHAM order by MASP desc
		end
GO

