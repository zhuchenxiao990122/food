package com.xw.project.entity;

import java.util.List;

/**
 * 
 * 功能描述:功能菜单节点 
 * 模块: 
 * 项目: 
 * 版本号:V1.0 
 * 部门:技术研发部 
 * 公司:杭州滨和信息科技有限公司 
 * 作者:吴滨
 * 邮箱:bin.wu@bhaps.cn 
 * 创建时间:2017年7月7日 下午3:36:17
 *************************************
 *************************************
 * 修改人: 修改时间: 修改内容: 1. 2.
 */
public class TreeNode {

	// 节点ID
	private String id;
	// 上级ID
	private String parentId;
	// 节点显示文本
	private String label;

	// 节点值
	private String value;

	// 是否叶子节点
	private Boolean leaf = true;

	// 节点层内序号
	private Integer sequence;

	// 节点显示图标样式名称
	private String icon;
	
	//大图标
	private String bigCls;

	// 节点默认是否展看
	private Boolean expanded = false;

	private Boolean checked = null;

	// key值
	private String key;

	// 页面的包路径
	private String modelView;

	// 功能URL
	private String funUrl;

	// 节点包含的子节点
	private List<TreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	public String getBigCls() {
		return bigCls;
	}

	public void setBigCls(String bigCls) {
		this.bigCls = bigCls;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getModelView() {
		return modelView;
	}

	public void setModelView(String modelView) {
		this.modelView = modelView;
	}

	public String getFunUrl() {
		return funUrl;
	}

	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}




	
}
