package search;

// 无序符号表（字典）的API
public interface DisorderedSymbolTable<Key,Value> {
    /**
     * 根据key获取value
     * @param key 不能为空，否则抛出异常
     * @throws IllegalArgumentException if {@code key} is {@code null}
     * @return val
     */
    Value get(Key key);

    /**
     * 将键值存入表内
     * @param key 不能为空，否则抛出异常
     * @param val 若val为空，则删除key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    void put(Key key, Value val);

    /**
     * 删除键值对
     * @param key key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    void delete(Key key);

    /**
     *  表中是否包含该key
     * @param key key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     * @return 是否
     */
    boolean contains(Key key);

    /**
     * 表是否为空
     * @return 是否
     */
    boolean isEmpty();

    /**
     * 表的键值对数
     * @return 数量
     */
    int size();

    /**
     * 表中所有键的集合
     * @return 集合
     */
    Iterable<Key> keys();

}
