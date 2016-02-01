package com.wind.commons;

/**
 * 分页代码上下文，主要采用ThreadLocal实现
 * 
 * @author ZhangYanchun
 * 
 */
public class SysContext {
  // 页码信息
  private static ThreadLocal<Integer> page = new ThreadLocal<Integer>();
  // 每页记录大小
  private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

  public static int getPage() {
    Object _page = page.get();
    if (_page == null) {
      return 0;
    }
    return (Integer) _page;
  }

  public static int getPageSize() {
    Object _pageSize = pageSize.get();
    if (_pageSize == null) {
      return 0;
    }
    return (Integer) _pageSize;
  }

  public static void removePage() {
    page.remove();
  }

  public static void removePageSize() {
    pageSize.remove();
  }

  public static void setPage(int _page) {
    page.set(_page);
  }

  public static void setPageSize(int _pageSize) {
    pageSize.set(_pageSize);
  }
}
