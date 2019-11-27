# Clinia Search API Client for Android

## Contributing

[Your contributions](https://github.com/clinia/cliniasearch-client-android/pull/new) are welcome! Please use our [formatting configuration](https://github.com/clinia/CodingStyle#android) to keep the coding style consistent.

## API Documentation

1. **[Contributing](#contributing)**


1. **[Install](#install)**


1. **[Quick Start](#quick-start)**


1. **[Search](#search)**

## Install

Install the Android client by adding the following dependency to your `Gradle` build file.

```gradle
dependencies {
    implementation 'ca.clinia:cliniasearch-android:1.+'
}
```

## Quick Start

The first step is to add a your application ID and Clinia API keys to your app. 

```gradle
 buildTypes {
        release {
            minifyEnabled true

            buildConfigField "String", "application", "APPLICATION_ID"
            buildConfigField "String", "apiKey", "API_KEY"
            buildConfigField "String", "endpoint", "CLINIA_ENDPOINT"

            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
```


Then add the CliniaViewModel to your Activity, and set it's environment variables.
The ViewModel can then be accessed from your Activity and its fragments.

```kotlin
class MyActivity : AppCompatActivity() {
private lateinit var viewModel: CliniaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity)

        viewModel = ViewModelProviders.of(this).get(CliniaViewModel::class.java)
        viewModel.setEnvironment(BuildConfig.application, BuildConfig.apiKey, BuildConfig.endpoint)
    }
}
```

In your Fragment:
```kotlin
class MyFragment : Fragment() {

    private lateinit var viewModel: CliniaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.my_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(CliniaViewModel::class.java)
        }
    }
}
```

Let's search by using the CliniaViewModel to call the search method, with a query and location:

```kotlin
fun search(query: String, location: String) {
    viewModel.setQuery(query)
    viewModel.setLocation(location)
    viewModel.getSearchData().observe(this, Observer { searchResponse ->
        //Handle the search response here
    })
}
```

## Getting Help

- **Found a bug?** You can open a [GitHub issue](https://github.com/clinia/cliniasearch-client-android/issues).
