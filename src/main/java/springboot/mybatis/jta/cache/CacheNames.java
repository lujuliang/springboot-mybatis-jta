package springboot.mybatis.jta.cache;

/**
 * Defines the caches used by @Cacheable methods.
 *
 */
public interface CacheNames {
	/**
	 * Cache defined for lookup values
	 */
	public static final String LOOKUP_CACHE = "lookupCache";

	public static final String LONG_LOOKUP_CACHE = "longLookupCache";

	public static final String ORGANIZATION_CACHE = "orgCache";

	public static final String ITEM_CATALOG_CACHE = "itemCatalogCache";

	public static final String ITEM_CATALOG_CACHE_BY_PROPERTY = "itemCatalogCacheByProperty";

	public static final String ITEM_CATALOG_CACHE_BY_ID = "itemCatalogCacheById";

	public static final String ITEM_CATALOG_CACHE_BY_MENU = "itemCatalogCacheByMenu";

	public static final String ITEM_CATALOG_CACHE_BY_PACAKGE = "itemCatalogCacheByPackage";

	public static final String ITEM_CATALOG_CACHE_BY_PACAKGE_MENU = "itemCatalogCacheByPackageMenu";

	public static final String KITCHEN_CACHE = "kitchenCache";

	public static final String HASH_CODE_CACHE_KEY_GENERATOR = "HashCodeCacheKeyGenerator";

	public static final String ITEM_CATALOG_CACHE_PACKAGE_MENU_MAP = "itemCatalogCachePackageMenuMap";

	public static final String ITEM_CATALOG_CACHE_ITEM_TOPIC_MAP = "itemCatalogCacheItemTopicMap";

	public static final String ITEM_CATALOG_CACHE_PRICE_POINT_MAP = "itemCatalogCachePricePointMap";

	public static final String ITEM_CATALOG_CACHE_BEVERAGE_TYPE_MAP = "itemCatalogCacheBeverageTypeMap";

}