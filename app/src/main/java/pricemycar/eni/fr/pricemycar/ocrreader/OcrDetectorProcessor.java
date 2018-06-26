/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pricemycar.eni.fr.pricemycar.ocrreader;

import android.util.Log;
import android.util.SparseArray;

import pricemycar.eni.fr.pricemycar.ocrreader.ui.camera.GraphicOverlay;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.PlateAPI;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.Vehicle;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A very simple Processor which gets detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> graphicOverlay;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        graphicOverlay = ocrGraphicOverlay;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        Pattern pattern = Pattern.compile("^[A-Za-z]{2}-[0-9]{3}-[A-Za-z]{2}([0-9]{2})?$");
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Matcher matcher = pattern.matcher(item.getValue());
                if(matcher.matches()) {
                    Log.d("OcrDetectorProcessor", "Plate detected! " + item.getValue());
                    OcrGraphic graphic = new OcrGraphic(graphicOverlay, item);
                    graphicOverlay.add(graphic);
                    PlateAPI plateAPI = new PlateAPI();
                    Vehicle vehicle = plateAPI.requestAPI(item.getValue(),null);
                }
            }
        }
    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        graphicOverlay.clear();
    }
}
