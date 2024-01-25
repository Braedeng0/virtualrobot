package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "hello", group = "MecanumBot")
public class HelloWorld extends LinearOpMode {
    DcMotor mUpRight;
    DcMotor mUpLeft;
    DcMotor mDownRight;
    DcMotor mDownLeft;

    @Override
    public void runOpMode() {
        mUpRight = hardwareMap.dcMotor.get("front_right_motor");
        mUpLeft = hardwareMap.dcMotor.get("front_left_motor");
        mDownRight = hardwareMap.dcMotor.get("back_right_motor");
        mDownLeft = hardwareMap.dcMotor.get("back_left_motor");
        mUpLeft.setDirection(DcMotor.Direction.REVERSE);
        mDownLeft.setDirection(DcMotor.Direction.REVERSE);
        mUpRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mUpLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mDownRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mDownLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            double xAmp = gamepad1.left_stick_x;
            double yAmp = -gamepad1.left_stick_y;
            double vector = Math.atan2(yAmp, xAmp);
            double amp = Math.sqrt((xAmp * xAmp + yAmp * yAmp));
            double power1 = Math.sin(vector - (Math.PI / 4)) * amp;
            double power2 = Math.sin(vector + (Math.PI / 4)) * amp;
            mUpLeft.setPower(power2);
            mDownRight.setPower(power2);
            mUpRight.setPower(power1);
            mDownLeft.setPower(power1);
            telemetry.addData("xAmp", xAmp);
            telemetry.addData("yAmp", yAmp);
            telemetry.addData("vector", vector);
            telemetry.addData("amp", amp);
            telemetry.addData("mUpLeft", power1);
            telemetry.addData("mUpRight", power2);
            telemetry.addData("mDownLeft", power2);
            telemetry.addData("mDownRight", power1);
            telemetry.update();
        }
    }
}