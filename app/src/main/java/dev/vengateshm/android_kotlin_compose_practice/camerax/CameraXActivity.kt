package dev.vengateshm.android_kotlin_compose_practice.camerax

/*
class CameraXActivity : ComponentActivity() {

    private val registerForActivityResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {

        }

    private lateinit var cameraExecutor: Executor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerForActivityResult.launch(android.Manifest.permission.CAMERA)
        cameraExecutor = Executors.newSingleThreadExecutor()
        setContent {
            MaterialTheme {

                val imageCapture = remember {
                    ImageCapture.Builder().build()
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    CameraPreview(modifier = Modifier.fillMaxSize(), imageCapture = imageCapture)
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter),
                        onClick = { captureImage(imageCapture) }) {
                        Image(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(128.dp),
                            painter = painterResource(id = R.drawable.baseline_lens_24),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }

    private fun captureImage(imageCapture: ImageCapture) {
        val file = File.createTempFile("img", ".jpg")
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        imageCapture.takePicture(outputFileOptions, cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Log.d("IMAGE_CAPTURE", outputFileResults.savedUri.toString())
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("IMAGE_CAPTURE", exception.message ?: "Error saving image")
                }
            })
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA,
    imageCapture: ImageCapture,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val previewView = PreviewView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                this.scaleType = scaleType
            }

            val previewUseCase = Preview.Builder().build()
            previewUseCase.setSurfaceProvider(previewView.surfaceProvider)

            scope.launch {
                val cameraProvider = context.cameraProvider()
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, previewUseCase,imageCapture)
            }

            previewView
        })
}

suspend fun Context.cameraProvider() = suspendCoroutine { continuation ->
    val listenableFuture = ProcessCameraProvider.getInstance(this)
    listenableFuture.addListener(
        {
            continuation.resume(listenableFuture.get())
        },
        ContextCompat.getMainExecutor(this)
    )
}*/
