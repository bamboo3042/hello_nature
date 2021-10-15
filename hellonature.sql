create table tb_magazines(
    idx number(10) primary key,
    show_flag char(1) not null,
    img varchar2(50) not null,
    title varchar2(30) not null,
    des varchar2(30) not null,
    content clob not null,
    type char(1) not null,
    cook_time varchar2(10),
    cook_kcal number(4),
    cook_level char(1),
    cook_ingredient varchar2(500),
    regdate date default sysdate
);

select * from tb_magazines;

create sequence magazines_seq
    increment by 1
    start with 1;

create table tb_addmins(
    idx number(10) primary key,
    name varchar2(5) not null,
    id varchar2(10) unique not null,
    password varchar2(20) not null,
    regdate date default sysdate
);

create sequence addmins_seq
    increment by 1
    start with 1;
    
create table tb_members(
    idx number(10) primary key,
    name varchar2(20) not null,
    email varchar2(30) unique not null,
    password varchar2(20) not null,
    hp varchar2(13) not null,
    birth varchar2(20),
    gender char(1),
    mhp_idx number(10) not null,
    green_flag char(1) default '0' not null,
    sms_flag char(1) default '0' not null,
    email_flag char(1) default '0' not null,
    hellocash number(10) default 0 not null,
    site number(1) not null,
    regdate date default sysdate
);

create sequence members_seq
    increment by 1
    start with 1;
    
create table tb_members_addresses(
    idx number(10) primary key,
    mem_idx number(10) not null,
    addr_name varchar2(20) not null,
    name varchar2(20) not null,
    hp varchar2(20) not null,
    zipcode varchar2(10) not null,
    addr1 varchar2(20) not null,
    addr2 varchar2(30) not null,
    request_type number(1) not null,
    request_memo1 varchar2(20),
    request_memo2 varchar2(30),
    dawn_flag number(1) default 0 not null,
    base_flag number(1) default 1 not null,
    gr_flag number(1) default 0 not null,
    regdate date default sysdate
);

create sequence members_addresses_seq
    increment by 1
    start with 1;

create table tb_members_coupons(
    idx number(10) primary key,
    mem_idx number(10) not null,
    ct_idx number(10) not null,
    used_flag number(1) default 0 not null,
    date_start varchar2(20) not null,
    date_end varchar2(20) not null,
    regdate date default sysdate
);

create sequence members_coupons_seq
    increment by 1
    start with 1;
    
create table tb_coupons_types(
    idx number(10) primary key,
    title varchar2(20) unique,
    target number(1) default 1 not null,
    auto number(1) not null,
    count number(7) not null,
    discount number(7) not null,
    min_price number(7) not null,
    date_start varchar2(20) not null,
    date_end varchar2(20) not null,
    type1 number(1) not null,
    type varchar2(50),
    regdate date default sysdate
);

create sequence coupons_types_seq
    increment by 1
    start with 1;

create table tb_members_baskets(
    idx number(10) primary key,
    mem_idx number(10),
    pro_idx number(10),
    pro_count number(5),
    regdate date default sysdate
);

create sequence members_baskets_seq
    increment by 1
    start with 1;
    
create table tb_cards(
    idx number(10) primary key,
    mem_idx number(10),
    num varchar2(30) unique,
    date varchar2(10) not null,
    cvc varchar2(4) not null,
    password varchar2(4) not null,
    base_flag number(1) default 0 not null,
    name varchar2(20) not null,
    bank number(2) not null,
    fav_flag number(1) default 0 not null,
    busi_flag number(1) default 0 not null,
    birth varchar2(10) not null,
    regdate date default sysdate
);

create sequence cards_seq
    increment by 1
    start with 1;
    
create table tb_products(
    idx number(10) primary key,
    name varchar2(20) unique,
    des varchar2(100) not null,
    br_idx number(10) not null,
    net_price number(10) not null,
    sale_price number(10),
    state number(1) default 0 not null,
    date_start varchar2(20) not null,
    date_end varchar2(20) not null,
    origin varchar2(10) not null,
    size_weight varchar2(20) not null,
    temp number(1) not null,
    count number(10) not null,
    delivery number(1) not null,
    packing number(1) not null,
    img1 varchar2(100) not null,
    img2 varchar2(100),
    img3 varchar2(100),
    img4 varchar2(100),
    pro_des clob not null,
    like number(10) default 0 not null,
    cate_idx number(10),
    eve_cate_idx number(10),
    pro_type varchar2(20) not null,
    pro_name varchar2(20) not null,
    food_type varchar2(20) not null,
    producer varchar2(20) not null,
    location varchar2(20) not null,
    date_built varchar2(20) not null,
    date_vaild varchar2(20) not null,
    regdate date default sysdate
);

create sequence products_seq
    increment by 1
    start with 1;

create table tb_products_questions(
    idx number(10) primary key,
    mem_idx number(10) not null,
    regdate date default sysdate,
    date_time varchar2(20) not null,
    title varchar2(20) not null,
    content clob not null,
    pro_idx number(10) not null,
    ans_flag number(1) default 0 not null,
    ans_content clob,
    ans_date varchar2(20)
);

create sequence products_questions_seq
    increment by 1
    start with 1;
    
create table tb_products_reviews(
    idx number(10) primary key,
    mem_idx number(10) unique,
    regdate date default sysdate,
    date_time varchar2(20) not null,
    like number(1) not null,
    content clob not null,
    pro_idx number(10) not null,
    ans_flag number(1) default 0 not null,
    ans_content clob,
    files varchar2(100),
    ans_date varchar2(20)
);

create sequence products_reviews_seqs
    increment by 1
    start with 1;

create table tb_categories(
    idx number(10) primary key,
    name varchar2(20) unique,
    root_idx number(10),
    img varchar2(100),
    life_flag number(1) default 0 not null,
    regdate date default sysdate
);

create sequence categories_seq
    increment by 1
    start with 1;