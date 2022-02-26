加载图片框架中，如果新添加一种数据源的时候的处理（比如在load时传入一个自己定义的数据结构  load(MyData)）

glide
1 图片加载流程
参见例子

coil 
1 图片加载流程
  
  进程运行时初始加载内核：ImageLoader  -> RealImageLoader

  其中注意RealImageLoader初始化时的变量：components
      override val components = componentRegistry.newBuilder()
        // Mappers
        .add(HttpUrlMapper())
        .add(StringMapper())
        .add(FileUriMapper())
        .add(ResourceUriMapper())
        .add(ResourceIntMapper())
        // Keyers
        .add(UriKeyer())
        .add(FileKeyer(options.addLastModifiedToFileCacheKey))
        // Fetchers
        .add(HttpUriFetcher.Factory(callFactoryLazy, diskCacheLazy, options.respectCacheHeaders))
        .add(FileFetcher.Factory())
        .add(AssetUriFetcher.Factory())
        .add(ContentUriFetcher.Factory())
        .add(ResourceUriFetcher.Factory())
        .add(DrawableFetcher.Factory())
        .add(BitmapFetcher.Factory())
        .add(ByteBufferFetcher.Factory())
        // Decoders
        .add(BitmapFactoryDecoder.Factory(options.bitmapFactoryMaxParallelism))
        .build()

  在这里面添加了四种类型的变量：   Mappers     Keyers Fetchers  Decoders
  其中我们主要关注下Mappers， Fetchers 及 Decoders
  1 Mappers 将load函数中传的数据转成下一步featch时需要的数据
  2 Fetchers通过mapper中转变后的数据，加载对应的图片数据
  3 将featch中加载的数据进行decoder
