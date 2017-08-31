package springboot.mybatis.jta.bean.database2;

import java.util.Date;

public class Income {

	private long id;
	private Integer userId;
	private Date operateDate;

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

}
