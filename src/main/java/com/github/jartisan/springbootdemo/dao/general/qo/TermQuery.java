package com.github.jartisan.springbootdemo.dao.general.qo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.jartisan.springbootdemo.dao.Page;


/***
 * 学期
 * @author wjl
 */
public class TermQuery extends Page implements java.io.Serializable {
	/***
	 * 业务编码
	 */
    private Integer code;
    /***
	 * 学期名称
	 */
    private String name;
    /***
   	 * 顺序号
   	 */
    private Integer seq;
    /***
   	 * 创建人用户名
   	 */
    private String createUserName;

    /***
   	 * 是否删除(0:未删除;1:已删除;)
   	 */
    private Integer isDelete;


    private static final long serialVersionUID = 1L;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
	}

}