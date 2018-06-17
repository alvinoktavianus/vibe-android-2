package id.co.vibe.vibe.base;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public interface BasePresenter<T> {

    void bind(T view);

    void unbind();

}
