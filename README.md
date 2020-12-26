# 🚀 Trending
Github Trending 

## ✨ Demo 

<p align="center">
  <img src="screen/demo.gif" width="360" height="640">
</p>

## ✨ screenshots
| Loading | Pull to refresh |  Item Touch |
|:-:|:-:|:-:|
| ![Fist](screen/screen1.png?raw=true) | ![3](screen/screen2.png?raw=true) | ![3](screen/screen3.png?raw=true) |
| Item Expanded | Menus |   Error State |
| ![4](screen/screen4.jpg?raw=true) | ![5](screen/screen5.png?raw=true) | ![6](screen/screen6.png?raw=true) |

## 🌟 Features
*   The app should fetch the trending repositories from the provided public API and display it to the users.
*   While the data is being fetched, the app should show a loading state [Shimmer animation] .
*   If the app is not able to fetch the data, then it should show an error state to the user with an option to retry again.
*   All the items in the list should be in their collapsed state by default and can be expanded on being tapped.
*   Tapping any item will expand it to show more details and collapse any other expanded item Tapping the same item in expanded state should collapse it.
*   The app should be able to handle configuration changes (like rotation)
*   The app should give a pull-to-refresh option to the user to force fetch data from remote.
*   MVVM with Android Architecture Components(DataBinding, LiveData, ViewModel)
*   Handle network status and network failures
*   Material design.

#### App Packages
* <b>network</b> - contains the api classes to make api calls to Github server, using Retrofit.
* <b>repository</b> - contains the repository classes.
* <b>di</b> - contains dependency injection classes, using Dagger2.
* <b>ui</b> - contains classes needed to display Activity.
* <b>util</b> - contains classes needed for activity redirection.


### Installing
Follow these steps if you want to get a local copy of the project on your machine.

#### 1. Clone or fork the repository by running the command below
```
https://github.com/islamelhady/trending
```


## 🤝 How to Contribute
1.  Fork it
2.  Create your feature branch (git checkout -b my-new-feature)
3.  Commit your changes (git commit -am 'Add some feature')
4.  Push to the branch (git push origin my-new-feature)
5.  Create new Pull Request

## 📃 Libraries used
*   Minimum SDK 19
*   [AndroidX](https://developer.android.com/jetpack/androidx/) - Previously known as 'Android support Library'
*   [Picasso](https://square.github.io/picasso/) - for loading and caching images
*   [Retrofit 2](https://github.com/square/retrofit) - for API integration.
*   [RxJava2](https://github.com/ReactiveX/RxJava) for implementing Observable pattern.
*   [Dagger 2](https://google.github.io/dagger/) for dependency injection.
*   [Gson](https://github.com/google/gson) - for serialization/deserialization Java Objects into JSON and back
*   [OkHttp](https://github.com/square/okhttp) - for implementing interceptor, logging and mocking web server.
*   [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
*   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
*   [DataBinding](https://developer.android.com/topic/libraries/data-binding/)
*   [CircleImageView](https://github.com/hdodenhof/CircleImageView)


## Authors

* **Islam Elhady** - *LinkedIn* - [Islam Elhady](https://www.linkedin.com/in/islamelhady)

```
MIT License

Copyright (c) 2020 Islam Elhady
```

