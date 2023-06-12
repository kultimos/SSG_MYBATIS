package com.kul.pojo.mapper;

import com.kul.pojo.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface SelectMapper {

    //����Ψһid��ѯ�����û���Ϣ
    User selectById(Integer id);

    List<User> selectAll();

    Integer selectCountByName(String name);


    /**
     * MapKey�����þ��ǿ���ָ����ѯ��������ĸ��ֶ���Ϊmap��key(ͨ�����Ƕ���ѡ������)
     * ���ĵĵ�����,�����ָ������,��ô���ض��map�������ʱ��,�����key�ظ������,�ͻᱨ��
     * ����������ȫ����,key��������Ψһ
     * ���������MapKeyע����Ҫ����һ��List<Map>�Ľṹ����ܽ��ն�������
     * ʹ��MapKeyע�����ֱ������һ��Map�ṹ�������ն�������
     * ʹ��Map���Դ���������ʱĳ������������ֶΣ�������������
     * @param id
     * @return
     */
    @MapKey("id")
    Map selectMapById(Integer id);
}
