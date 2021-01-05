package com.example.learnkt.rx;


import com.example.learnkt.bean.BaseEntity;

import java.util.List;

import io.reactivex.Observable;


/**
 * 记录单一权限的状态
 * {@link Permission#name}权限名称，也可以通过{@link Permission#combineName(List)}方法合并多个请求名称
 * {@link Permission#granted}权限是否开启
 * {@link Permission#shouldShowRequestPermissionRationale}用户是否点击了"不再提醒"，如果是true则还可以不断弹出申请权限窗口
 *
 */
public class Permission extends BaseEntity {
    //权限名称
    public final String name;
    //权限是否开启
    public final boolean granted;
    //权限是否允许不断提醒开启
    public final boolean shouldShowRequestPermissionRationale;

    public Permission(String name, boolean granted) {
        this(name, granted, false);
    }

    public Permission(String name, boolean granted, boolean shouldShowRequestPermissionRationale) {
        this.name = name;
        this.granted = granted;
        this.shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale;
    }

    public Permission(List<Permission> permissions) {
        name = combineName(permissions);
        granted = combineGranted(permissions);
        shouldShowRequestPermissionRationale = combineShouldShowRequestPermissionRationale(permissions);
    }

    @Override
    @SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Permission that = (Permission) o;

        if (granted != that.granted) return false;
        if (shouldShowRequestPermissionRationale != that.shouldShowRequestPermissionRationale)
            return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (granted ? 1 : 0);
        result = 31 * result + (shouldShowRequestPermissionRationale ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", granted=" + granted +
                ", shouldShowRequestPermissionRationale=" + shouldShowRequestPermissionRationale +
                '}';
    }

    /**
     * 合并多个请求名称，这里的操作就更加偏向函数式了，而并非仅仅按照传统的subscribe()了
     * @param permissions 多个请求名称列表{@link List}
     * @return 合并名称结果
     */
    private String combineName(List<Permission> permissions) {
        return Observable.fromIterable(permissions)
                .map(permission -> permission.name)
                .collectInto(new StringBuilder(), (s, s2) -> {
                    if (s.length() == 0) {
                        s.append(s2);
                    } else {
                        s.append(", ").append(s2);
                    }
                })
                .blockingGet()
                .toString();
    }

    /**
     * 筛选规则，all基本操作，尝试合并所有权限的请求情况
     * @param permissions 权限允许
     * @return 权限体的请求情况
     */
    private Boolean combineGranted(List<Permission> permissions) {
        return Observable.fromIterable(permissions)
                .all(permission -> permission.granted)
                .blockingGet();
    }

    /**
     * 和权限请求情况{@link Permission#combineGranted(List)}的逻辑类似，只要有一个权限是允许权限请求框弹出的，
     * 就表示对于全部请求的请求框允许增加了宽容性
     * @param permissions 处理的权限
     * @return 权限体对于请求框弹出是否允许
     */
    private Boolean combineShouldShowRequestPermissionRationale(List<Permission> permissions) {
        return Observable.fromIterable(permissions)
                .any(permission -> permission.shouldShowRequestPermissionRationale)
                .blockingGet();
    }
}
