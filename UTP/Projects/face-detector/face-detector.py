import cv2

# Load some pre-trained data on face frontals from opencv (haar cascade algorithm)
trained_face_data = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

"""
# Choose an image to detect faces in
img = cv2.imread('RDJ.jpg')

# Must convert to grayscale
grayscale_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

# Detect faces
face_coordinates = trained_face_data.detectMultiScale(grayscale_img)

# Draw rectangles around the faces
for (x, y, w, h) in face_coordinates:
    cv2.rectangle(img, (x, y), (x+w, y+h), (0,255, 0), 2)

# Display the image with the faces spotted
cv2.imshow('Face Detector', img)

# Listen for a key to be presses for 1 millisecond, then move on
cv2.waitKey()
"""



# Capture video from webcam.
webcam = cv2.VideoCapture(0)

# Iterate forever over frames
while True:

    # Read the current frame
    successful_frame_read, frame = webcam.read()

    # Must convert to grayscale
    grayscale_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    # Detect faces
    face_coordinates = trained_face_data.detectMultiScale(grayscale_frame)

    # Draw rectangles around the faces
    for (x, y, w, h) in face_coordinates:
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0,255, 0), 2)

    # Display the image with the faces spotted
    cv2.imshow('Face Detector', frame)

    # Listen for a key to be presses for 1 millisecond, then move on
    key = cv2.waitKey(1)

    # Stop if Q key is pressed
    if key==81 or key==113 or key==27:
        break

# release the Video Capture object
webcam.release()
