package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.opencv.core.Mat;

@TeleOp(name="racoon", group="Iterative Opmode")
public class RacoonOp extends OpMode {
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public ColorSensor wheelTracker;
    public int wheelSpinCounter = 0;

    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftMotor");
        rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");
        wheelTracker = hardwareMap.get(ColorSensor.class, "wheelTracker");

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        double y = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double leftPower = y+turn;
        double rightPower = y-turn;
        if(Math.abs(leftPower) > 1 || Math.abs(rightPower) > 1){
            if(Math.abs(leftPower) > Math.abs(rightPower)){
                leftPower /= Math.abs(leftPower);
                rightPower /= Math.abs(leftPower);
            } else{
                leftPower /= Math.abs(rightPower);
                rightPower /= Math.abs(rightPower);
            }
        }
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        if (wheelTracker.red() > 750 && wheelTracker.red() < 1000) {
            if (wheelTracker.green() > 2100 && wheelTracker.green() < 2800) {
                if (wheelTracker.blue() > 5600 && wheelTracker.blue() < 7400) {
                    wheelSpinCounter += 1;
                }
            }
        }

        telemetry.addData("leftMotorPower", leftPower);
        telemetry.addData("rightMotorPower", rightPower);
        telemetry.addData("redVal", wheelTracker.red());
        telemetry.addData("greenVal", wheelTracker.green());
        telemetry.addData("blueVal", wheelTracker.blue());
        telemetry.addData("wheelSpinCounter", wheelSpinCounter);
    }
}
