# SpaceRocket
Shows the list and details about [SpaceX Rocket](https://github.com/r-spacex/SpaceX-API).

## Video Walkthrough
https://user-images.githubusercontent.com/1322567/225858428-cf48530f-5199-4937-890e-dee34a4c086e.mp4

## Note
In the requirement there were two api,  however found that rocket detail api returns the same response that we get in list of rockets. That is the reason I didn't implement detail api.

1. Api for listing rocket:
https://api.spacexdata.com/v4/rockets
2. Api for rocket detail:
https://api.spacexdata.com/v4/rockets/:id

## Tech Stack
- Android Architecture components like: ViewModel, LiveData, RoomDB, Navigation Component etc.
- Followed MVVM Android Architecture 
- Data cached offline using RoomDb and handled using Repository pattern
- Usages Hilt-Android as a dependency injection
- Using Coroutines to deal with IO operations.
- Usage 100% Kotlin for this project



## What next
- Ux Improvement like shimmer view and styling
- Unit test
