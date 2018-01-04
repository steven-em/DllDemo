package com.rljc.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 已登记的人员
 * @author hzq
 */
@Entity
@Table(name="registered_men")
public class RegisteredMen extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(columnDefinition="mediumtext COMMENT '图片的base64位base64位数据'")
	private String image1; //登记的图片，base64的数据格式
	
	private Integer apiAccountId; //绑定的账户主键
	
	@ManyToOne
	@JoinColumn(name = "apiAccountId", referencedColumnName = "id", insertable=false, updatable=false)  
	private ApiAccount apiAccount;
	
	private Integer category;//标识图片类别（1 - 手机自拍照片，2 - 身份证照片，3 - 护照照片，默认为手机自拍照片）
	
	private Double faceRectWidth;//人脸轮廓宽度
	private Double faceRectHeight;//人脸轮廓高度。
	
	//坐标格式 x-y
	private String faceRectTopLeft; //以浮点数表示的人脸轮廓左上角坐标。
	private String eyeLeft; //以浮点数表示的左眼坐标。
	private String eyeRight; //以浮点数表示的右眼坐标。
	private String mouthCenter;//以浮点数表示的嘴巴坐标。
	
	private Double blurMotion; //运动模糊
	private Double blurGaussian; //高斯模糊
	private Double deflectionH; //水平偏角
	private Double deflectionV;//上下偏角
	 
	private String contentType; //图片类型jpg, png

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public Integer getApiAccountId() {
		return apiAccountId;
	}

	public void setApiAccountId(Integer apiAccountId) {
		this.apiAccountId = apiAccountId;
	}

	public ApiAccount getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(ApiAccount apiAccount) {
		this.apiAccount = apiAccount;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}



	public Double getFaceRectWidth() {
		return faceRectWidth;
	}

	public void setFaceRectWidth(Double faceRectWidth) {
		this.faceRectWidth = faceRectWidth;
	}

	public Double getFaceRectHeight() {
		return faceRectHeight;
	}

	public void setFaceRectHeight(Double faceRectHeight) {
		this.faceRectHeight = faceRectHeight;
	}


	
	public String getFaceRectTopLeft() {
		return faceRectTopLeft;
	}

	public void setFaceRectTopLeft(String faceRectTopLeft) {
		this.faceRectTopLeft = faceRectTopLeft;
	}

	public String getEyeLeft() {
		return eyeLeft;
	}

	public void setEyeLeft(String eyeLeft) {
		this.eyeLeft = eyeLeft;
	}

	public String getEyeRight() {
		return eyeRight;
	}

	public void setEyeRight(String eyeRight) {
		this.eyeRight = eyeRight;
	}

	public String getMouthCenter() {
		return mouthCenter;
	}

	public void setMouthCenter(String mouthCenter) {
		this.mouthCenter = mouthCenter;
	}


	public Double getBlurMotion() {
		return blurMotion;
	}

	public void setBlurMotion(Double blurMotion) {
		this.blurMotion = blurMotion;
	}

	public Double getBlurGaussian() {
		return blurGaussian;
	}

	public void setBlurGaussian(Double blurGaussian) {
		this.blurGaussian = blurGaussian;
	}

	public Double getDeflectionH() {
		return deflectionH;
	}

	public void setDeflectionH(Double deflectionH) {
		this.deflectionH = deflectionH;
	}

	public Double getDeflectionV() {
		return deflectionV;
	}

	public void setDeflectionV(Double deflectionV) {
		this.deflectionV = deflectionV;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}
