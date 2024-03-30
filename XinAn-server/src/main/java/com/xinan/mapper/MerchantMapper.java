package com.xinan.mapper;

import com.xinan.entity.Merchant;
import com.xinan.entity.MerchantAddress;
import com.xinan.entity.MerchantCategory;
import com.xinan.entity.UserMerchant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MerchantMapper {

    @Select("select merchant_id from XinAn.user_merchant where user_id = #{id}")
    List<Long> listFavoritesIds(Long id);

    @Select("select id, name, merchant_category_id, merchant_address_id, leader, phone from XinAn.merchant where id = #{id}")
    Merchant getById(Long id);

    @Select("select id, city, district, detail from XinAn.merchant_address where id = #{id}")
    MerchantAddress getAddressById(Long id);

    @Select("select id, name from XinAn.merchant_category where id = #{id}")
    MerchantCategory getCategoryById(Long id);

    @Insert("insert into XinAn.user_merchant (user_id, merchant_id, create_time) values " +
            "(#{userId},#{merchantId},#{createTime})")
    void addFavorite(UserMerchant userMerchant);
}
