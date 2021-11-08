package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Anaya: AutonTimeBased", group="Linear Opmode")
public class AutonTimeBased extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx leftFrontDrive = null;
    private DcMotorEx rightFrontDrive = null;
    private DcMotorEx leftBackDrive = null;
    private DcMotorEx rightBackDrive = null;
    private DcMotorEx intakeDrive = null;
    private Servo carouselServo = null;

    @Override
    public void runOpMode() {

        leftFrontDrive  = hardwareMap.get(DcMotorEx.class, "left_front");
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, "right_front");
        leftBackDrive  = hardwareMap.get(DcMotorEx.class, "left_back");
        rightBackDrive = hardwareMap.get(DcMotorEx.class, "right_back");
        intakeDrive = hardwareMap.get(DcMotorEx.class, "intake");
        carouselServo = hardwareMap.get(Servo.class, "carousel");
        rightBackDrive.setDirection(DcMotorEx.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotorEx.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotorEx.Direction.REVERSE);
        intakeDrive.setDirection(DcMotorEx.Direction.REVERSE);
        carouselServo.setDirection(Servo.Direction.FORWARD);



        AutonMethods methods = new AutonMethods(this, telemetry, leftFrontDrive,  rightFrontDrive, leftBackDrive, rightBackDrive, intakeDrive, carouselServo);

        while (runtime.seconds() < 30) {
            methods.goForward(2, 3100);
            methods.goBackwards(2, 3100);
            methods.leftTurn(1, 3100);
            methods.rightTurn(1, 3100);
            methods.turnCarousel(3);
            methods.intakeIn(2, 1000);
            methods.intakeOut(3, 1000);

        }


        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left front (%.2f)", leftFrontDrive.getVelocity());
        telemetry.addData("Motors", "right front (%.2f)", rightFrontDrive.getVelocity());
        telemetry.addData("Motors", "left back (%.2f)", leftBackDrive.getVelocity());
        telemetry.addData("Motors", "right back (%.2f)", rightBackDrive.getVelocity());

        telemetry.update();
    }
}