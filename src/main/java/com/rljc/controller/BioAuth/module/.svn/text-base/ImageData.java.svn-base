package com.rljc.controller.BioAuth.module;

public class ImageData {
	
	private Integer category;
	
	public class LandmarkTerminal{
		
		private Double face_rect_width;
		
		private Double face_rect_height;

		private Coordinate face_rect_top_left;
		
		private Coordinate eye_left;
		
		private Coordinate eye_right;
		
		private Coordinate mouth_center;
		
		public LandmarkTerminal(){
			
			this.face_rect_width  = 0.00;
			this.face_rect_height = 0.00;
			this.face_rect_top_left = new Coordinate();
			this.eye_left = new Coordinate();
			this.eye_right = new Coordinate();
			this.mouth_center = new Coordinate();
		}

		public Coordinate getFace_rect_top_left() {
			return face_rect_top_left;
		}

		public void setFace_rect_top_left(Coordinate face_rect_top_left) {
			this.face_rect_top_left = face_rect_top_left;
		}

		public Double getFace_rect_width() {
			return face_rect_width;
		}

		public void setFace_rect_width(Double face_rect_width) {
			this.face_rect_width = face_rect_width;
		}

		public Double getFace_rect_height() {
			return face_rect_height;
		}

		public void setFace_rect_height(Double face_rect_height) {
			this.face_rect_height = face_rect_height;
		}

		public Coordinate getEye_left() {
			return eye_left;
		}

		public void setEye_left(Coordinate eye_left) {
			this.eye_left = eye_left;
		}

		public Coordinate getEye_right() {
			return eye_right;
		}

		public void setEye_right(Coordinate eye_right) {
			this.eye_right = eye_right;
		}

		public Coordinate getMouth_center() {
			return mouth_center;
		}

		public void setMouth_center(Coordinate mouth_center) {
			this.mouth_center = mouth_center;
		}
	}
	
	private LandmarkTerminal landmark_terminal;
	
	public class QualityTerminal{
		
		private Double brightness;
		private Double blur_motion;
		private Double blur_gaussian;
		private Double deflection_h;
		private Double deflection_v;
		
		public QualityTerminal(){
			
			this.brightness = 0.00;
			this.blur_motion = 0.00;
			this.blur_gaussian = 0.00;
			this.deflection_h = 0.00;
			this.deflection_v =  0.00;
		}
		public Double getBrightness() {
			return brightness;
		}
		public void setBrightness(Double brightness) {
			this.brightness = brightness;
		}
		public Double getBlur_motion() {
			return blur_motion;
		}
		public void setBlur_motion(Double blur_motion) {
			this.blur_motion = blur_motion;
		}
		public Double getBlur_gaussian() {
			return blur_gaussian;
		}
		public void setBlur_gaussian(Double blur_gaussian) {
			this.blur_gaussian = blur_gaussian;
		}
		public Double getDeflection_h() {
			return deflection_h;
		}
		public void setDeflection_h(Double deflection_h) {
			this.deflection_h = deflection_h;
		}
		public Double getDeflection_v() {
			return deflection_v;
		}
		public void setDeflection_v(Double deflection_v) {
			this.deflection_v = deflection_v;
		}
		
	}
	
	
	public QualityTerminal quality_terminal;
	
	
	private String content_type;
	
	private String data;
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}

	public LandmarkTerminal getLandmark_terminal() {
		return landmark_terminal;
	}
	public void setLandmark_terminal(LandmarkTerminal landmark_terminal) {
		this.landmark_terminal = landmark_terminal;
	}
	public QualityTerminal getQuality_terminal() {
		return quality_terminal;
	}
	public void setQuality_terminal(QualityTerminal quality_terminal) {
		this.quality_terminal = quality_terminal;
	}

	
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public ImageData(String data){
		this.category  = 1;
		this.content_type = "jpg";
		this.data = data;
		this.landmark_terminal = new LandmarkTerminal();
	}
	
}
