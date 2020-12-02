package com.xw.project.entity;

import lombok.Data;

/**
 * 新闻发布-实体类
 * @author weiLiang
 * @since 2020-06-30
 */
@Data
public class NewsRelease {
    private String newsTitle;
          private String newsSubTitle;
          private String newsBrief;
          private String newsNote;
          private String[] filesId;
}
