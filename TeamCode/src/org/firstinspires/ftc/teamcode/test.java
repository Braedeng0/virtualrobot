package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "test", group = "MecanumBot")
public class test extends LinearOpMode {
    DcMotor motorUpLeft;
    DcMotor motorDownLeft;
    DcMotor motorUpRight;
    DcMotor motorDownRight;
    // Setup stuff
    @Override
    public void runOpMode(){

        motorUpLeft = hardwareMap.dcMotor.get("front_left_motor");
        motorDownLeft = hardwareMap.dcMotor.get("back_left_motor");
        motorUpRight = hardwareMap.dcMotor.get("front_right_motor");
        motorDownRight = hardwareMap.dcMotor.get("back_right_motor");
        motorUpLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDownLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorUpLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorDownLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorUpRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorDownRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            double stick_x = gamepad1.left_stick_x;
            double stick_y = -1*gamepad1.left_stick_y;
            double degree = Math.atan2(stick_y, stick_x);
            double magnitude = Math.sqrt(Math.pow(stick_x,2) + Math.pow(stick_y,2));
            //Power 1: Upper left and lower right motors
            double power1 = Math.sin(degree+(Math.PI/4))*magnitude;
            //Power 2: Lower left and upper right motors
            double power2 = Math.sin(degree-(Math.PI/4))*magnitude;
            motorUpLeft.setPower(power2);
            motorDownRight.setPower(power2);
            motorDownLeft.setPower(power1);
            motorUpRight.setPower(power1);
            telemetry.addData("Degree", degree);
            telemetry.addData("Magnitude", magnitude);
            telemetry.addData("Stick X Position", stick_x);
            telemetry.addData("Stick Y Position", stick_y);
            telemetry.addData("Up Left Motor", power1);
            telemetry.addData("Down Left Motor", power2);
            telemetry.addData("Up Right Motor", power2);
            telemetry.addData("Down Right Motor", power1);
            telemetry.update();
        }
    }
}