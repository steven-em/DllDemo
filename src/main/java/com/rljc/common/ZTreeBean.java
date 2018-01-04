package com.rljc.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;

public class ZTreeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private boolean isParent;// 记录 treeNode 节点是否为父节点
	private List<ZTreeBean> children = new Vector<ZTreeBean>();// 节点的子节点数据集合
	private boolean checked;// 节点的 checkBox / radio 的 勾选状态
	private boolean chkDisabled;// 设置节点的 checkbox / radio 是否禁用
	private boolean halfCheck;// 强制节点的 checkBox / radio 的 半勾选状态
	private String icon;// 节点自定义图标的 URL 路径
	private String iconClose;// 父节点自定义折叠时图标的 URL 路径
	private String iconOpen;// 父节点自定义展开时图标的 URL 路径
	private String iconSkin;// 节点自定义图标的 className
	private boolean nocheck;// 设置节点是否隐藏 checkbox / radio
	private boolean open;// 记录 treeNode 节点的 展开 / 折叠 状态
	private String target;// 设置点击节点后在何处打开 url。[treeNode.url 存在时有效]
	private String url;// 节点链接的目标 URL
	private Map<String, Object> diy = new HashMap<String, Object>();// 自定义属性
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public void setChildren(List<ZTreeBean> children) {
		this.children = children;
	}

	public List<ZTreeBean> getChildren() {
		return children;
	}

	public void addChildren(ZTreeBean child) {
		this.children.add(child);
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean getHalfCheck() {
		return halfCheck;
	}

	public void setHalfCheck(boolean halfCheck) {
		this.halfCheck = halfCheck;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, Object> getDiy() {
		return diy;
	}

	public void putDiy(String key, Object value) {
		this.diy.put(key, value);
	}

	public Map<String, Object> toMap() {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> this_ = BeanUtils.describe(this);
			this_.remove("diy");
			this_.remove("class");
			if (this.diy != null) {
				this_.putAll(this.diy);
			}
			Iterator<Entry<String, Object>> itEnt = this_.entrySet().iterator();
			while (itEnt.hasNext()) {
				Entry<String, Object> ent = itEnt.next();
				if (ent.getValue() != null)
					if (ent.getValue() instanceof String) {
						this_.put(ent.getKey(), "'" + ent.getValue() + "'");
					}
			}
			return this_;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}

	@Override
	public String toString() {
		List<ZTreeBean> childrenBak = this.children;

		this.children = null;
		Map<String, Object> m = this.toMap();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (childrenBak != null) {
			for (int i = 0; i < childrenBak.size(); i++) {
				sb.append(childrenBak.get(i).toString());

				if (i != childrenBak.size() - 1) {
					sb.append(",");
				} else {
				}
			}
		}
		sb.append("]");
		m.put("children", sb.toString());

		return m.toString().replaceAll("=", ":").replaceAll("'true'", "true")
				.replaceAll("'false'", "false");
	}
}
