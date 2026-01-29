package org.firstinspires.ftc.teamcode.resources.vision;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class AprilTagDetectionPipeline {
    private AprilTagProcessor processor;
    private VisionPortal visionPortal;
    private List<AprilTagDetection> detections = new ArrayList<>();
    private Telemetry telemetry;

    public void init(HardwareMap hMap, Telemetry telemetry) {
        /* processor config */
        processor = new AprilTagProcessor.Builder()
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();
        /* api config */
        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hMap.get(WebcamName.class, "Camera 1"));
        builder.setCameraResolution(new Size(640, 480));
        builder.addProcessor(processor);
        /* initializations */
        visionPortal = builder.build();
        this.telemetry = telemetry;
    }

    public void update() {
        detections = processor.getDetections();
    }

    public List<AprilTagDetection> getDetectedTags() {
        return detections;
    }

    public void displayTelemetry(AprilTagDetection detectedID) {
        if (detectedID == null) {return;}
        //finish method later, prioritize auto
    }

    public AprilTagDetection getTagByID(int id) {
        for (AprilTagDetection detection : detections) {
            if (detection.id == id) {
                return detection;
            }
        }
        return null;
    }

    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}
