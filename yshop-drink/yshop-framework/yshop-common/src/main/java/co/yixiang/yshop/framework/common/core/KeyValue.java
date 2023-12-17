package co.yixiang.yshop.framework.common.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Key Value 的键值对
 *
 * @author yshop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K, V> {

    private K key;
    private V value;

}
