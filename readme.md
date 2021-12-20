# Demo
## 安装使用说明  
* 安装apks中服务apk
* 通过`IMLibClient.getInstance().connect`方法连接到服务
```kotlin 
        //替换自己的key
        IMLibClient.getInstance().registerClient("F9C20F5F4667B033355EEED7E14B57DC")
        IMLibClient.getInstance().connect(this,object :IMConnectListener{
            override fun connected() {
                //连接上
                isConnect.set(true)

            }
            override fun disConnected() {
                isConnect.set(false)
            }
        })

```

* 代理接口并使用

```kotlin

val func=IMLibClient.getInstance().buildClientFunc(ITMCService::class.java)

val response = func.getAngle1(TMC.InclinePrg.TMC_AUTO_INC)//必须在子线程中调用
    if(reponse.isSuccess()){//必须判断成功
      //成功后获取data
    }

```
该代理接口可以在连接前创建，目前支持接口类有: 
`IAUTService`,`IAUSService`,`ICSVService`,`IEDMService`,`IBMMService`,`IBaseFuncService`
,`IMOTService`,`ISubService`,`ITMCService`

**必须注意**所有接口中的方法调用必须在子线程中使用

**必须注意**所有接口方法必须判断response成功后，才能获取数据，否则数据字段可能为空
* 关于`Response`说明
```java
    public int errorCode;//0是成功状态，其他值参考GRCCode类定义
    public String errorMsg;
    public T data;
    public Response() {
    }
```
* GRCCode
``` java
    public static final int GRC_OK = 0;// 0x0 Function successfully completed.
    public static final int GRC_UNDEFINED = 1;// 0x1 Unknown error, result unspecified.
    public static final int GRC_IVPARAM = 2;// 0x2 Invalid parameter detected. Result unspecified.
    public static final int GRC_IVRESULT = 3;// 0x3 Invalid result.
    public static final int GRC_FATAL = 4;// 0x4 Fatal error.
    public static final int GRC_NOT_IMPL = 5;// 0x5 Not implemented yet.
    public static final int GRC_TIME_OUT = 6;// 0x6 Function execution timed out. Result unspecified.
    public static final int GRC_SET_INCOMPL = 7;// 0x7 Parameter setup for subsystem is incomplete.
    public static final int GRC_ABORT = 8;// 0x8 Function execution has been aborted.
    public static final int GRC_NOMEMORY = 9;// 0x9 Fatal error - not enough memory.
    public static final int GRC_NOTINIT = 10;// 0xA Fatal error - subsystem not initialized.
    public static final int GRC_SHUT_DOWN = 12;// 0xC Subsystem is down.
    public static final int GRC_SYSBUSY = 13;// 0xD System busy/already in use of another process. Cannot execute function.
    public static final int GRC_HWFAILURE = 14;// 0xE Fatal error - hardware failure.
    public static final int GRC_ABORT_APPL = 15;// 0xF Execution of application has been aborted (SHIFT-ESC).
    public static final int GRC_LOW_POWER = 16;// 0x10 Operation aborted - insufficient power supply level.
    public static final int GRC_IVVERSION = 17;// 0x11 Invalid version of file, ...
    public static final int GRC_BATT_EMPTY = 18;// 0x12 Battery empty
    public static final int GRC_NO_EVENT = 20;// 0x14 no event pending.
    public static final int GRC_OUT_OF_TEMP = 21;// 0x15 out of temperature range
    public static final int GRC_INSTRUMENT_TILT = 22;// 0x16 instrument tilting out of range
    public static final int GRC_COM_SETTING = 23;// 0x17 communication error
    public static final int GRC_NO_ACTION = 24;// 0x18 GRC_TYPE Input 'do no action'
    public static final int GRC_SLEEP_MODE = 25;// 0x19 Instr. run into the sleep mode
    public static final int GRC_NOTOK = 26;// 0x1A Function not successfully completed.
    public static final int GRC_NA = 27;// 0x1B Not available
    public static final int GRC_OVERFLOW = 28;// 0x1C Overflow error
    public static final int GRC_STOPPED = 29;// 0x1D System or subsystem has been stopped
    public static final int GRC_ANG_ERROR = 257;// 0x101 Angles and Inclinations not valid
    public static final int GRC_ANG_INCL_ERROR = 258;// 0x102 inclinations not valid
    public static final int GRC_ANG_BAD_ACC = 259;// 0x103 value accuracies not reached
    public static final int GRC_ANG_BAD_ANGLE_ACC = 260;// 0x104 angle-accuracies not reached
    public static final int GRC_ANG_BAD_INCLIN_ACC = 261;// 0x105 inclination accuracies not reached
    public static final int GRC_ANG_WRITE_PROTECTED = 266;// 0x10A no write access allowed
    public static final int GRC_ANG_OUT_OF_RANGE = 267;// 0x10B value out of range
    public static final int GRC_ANG_IR_OCCURED = 268;// 0x10C function aborted due to interrupt
    public static final int GRC_ANG_HZ_MOVED = 269;// 0x10D hz moved during incline measurement
    public static final int GRC_ANG_OS_ERROR = 270;// 0x10E troubles with operation system
    public static final int GRC_ANG_DATA_ERROR = 271;// 0x10F overflow at parameter values
    public static final int GRC_ANG_PEAK_CNT_UFL = 272;// 0x110 too less peaks
    public static final int GRC_ANG_TIME_OUT = 273;// 0x111 reading timeout
    public static final int GRC_ANG_TOO_MANY_EXPOS = 274;// 0x112 too many exposures wanted
    public static final int GRC_ANG_PIX_CTRL_ERR = 275;// 0x113 picture height out of range
    public static final int GRC_ANG_MAX_POS_SKIP = 276;// 0x114 positive exposure dynamic overflow
    public static final int GRC_ANG_MAX_NEG_SKIP = 277;// 0x115 negative exposure dynamic overflow
    public static final int GRC_ANG_EXP_LIMIT = 278;// 0x116 exposure time overflow
    public static final int GRC_ANG_UNDER_EXPOSURE = 279;// 0x117 picture underexposured
    public static final int GRC_ANG_OVER_EXPOSURE = 280;// 0x118 picture overexposured
    public static final int GRC_ANG_TMANY_PEAKS = 300;// 0x12C too many peaks detected
    public static final int GRC_ANG_TLESS_PEAKS = 301;// 0x12D too less peaks detected
    public static final int GRC_ANG_PEAK_TOO_SLIM = 302;// 0x12E peak too slim
    public static final int GRC_ANG_PEAK_TOO_WIDE = 303;// 0x12F peak to wide
    public static final int GRC_ANG_BAD_PEAKDIFF = 304;// 0x130 bad peak difference
    public static final int GRC_ANG_UNDER_EXP_PICT = 305;// 0x131 too less peak amplitude
    public static final int GRC_ANG_PEAKS_INHOMOGEN = 306;// 0x132 inhomogeneous peak amplitudes
    public static final int GRC_ANG_NO_DECOD_POSS = 307;// 0x133 no peak decoding possible
    public static final int GRC_ANG_UNSTABLE_DECOD = 308;// 0x134 peak decoding not stable
    public static final int GRC_ANG_TLESS_FPEAKS = 309;// 0x135 too less valid finepeaks
    public static final int GRC_ANG_INCL_OLD_PLANE = 316;// 0x13C inclination plane out of time range
    public static final int GRC_ANG_INCL_NO_PLANE = 317;// 0x13D inclination no plane available
    public static final int GRC_ANG_FAST_ANG_ERR = 326;// 0x146 errors in 5kHz and or 2.5kHz angle
    public static final int GRC_ANG_FAST_ANG_ERR_5 = 327;// 0x147 errors in 5kHz angle
    public static final int GRC_ANG_FAST_ANG_ERR_25 = 328;// 0x148 errors in 2.5kHz angle
    public static final int GRC_ANG_TRANS_ERR = 329;// 0x149 LVDS transfer error detected
    public static final int GRC_ANG_TRANS_ERR_5 = 330;// 0x14A LVDS transfer error detected in 5kHz mode
    public static final int GRC_ANG_TRANS_ERR_25 = 331;// 0x14B LVDS transfer error detected in 2.5kHz mode
    public static final int GRC_ATA_NOT_READY = 512;// 0x200 ATR-System is not ready.
    public static final int GRC_ATA_NO_RESULT = 513;// 0x201 Result isn't available yet.
    public static final int GRC_ATA_SEVERAL_TARGETS = 514;// 0x202 Several Targets detected.
    public static final int GRC_ATA_BIG_SPOT = 515;// 0x203 Spot is too big for analyse.
    public static final int GRC_ATA_BACKGROUND = 516;// 0x204 Background is too bright.
    public static final int GRC_ATA_NO_TARGETS = 517;// 0x205 No targets detected.
    public static final int GRC_ATA_NOT_ACCURAT = 518;// 0x206 Accuracy worse than asked for.
    public static final int GRC_ATA_SPOT_ON_EDGE = 519;// 0x207 Spot is on the edge of the sensing area.
    public static final int GRC_ATA_BLOOMING = 522;// 0x20A Blooming or spot on edge detected.
    public static final int GRC_ATA_NOT_BUSY = 523;// 0x20B ATR isn't in a continuous mode.
    public static final int GRC_ATA_STRANGE_LIGHT = 524;// 0x20C Not the spot of the own target illuminator.
    public static final int GRC_ATA_V24_FAIL = 525;// 0x20D Communication error to sensor (ATR).
    public static final int GRC_ATA_DECODE_ERROR = 526;// 0x20E Received Arguments cannot be decoded
    public static final int GRC_ATA_HZ_FAIL = 527;// 0x20F No Spot detected in Hz-direction.
    public static final int GRC_ATA_V_FAIL = 528;// 0x210 No Spot detected in V-direction.
    public static final int GRC_ATA_HZ_STRANGE_L = 529;// 0x211 Strange light in Hz-direction.
    public static final int GRC_ATA_V_STRANGE_L = 530;// 0x212 Strange light in V-direction.
    public static final int GRC_ATA_SLDR_TRANSFER_PENDING = 531;// 0x213 On multiple ATA_SLDR_OpenTransfer.
    public static final int GRC_ATA_SLDR_TRANSFER_ILLEGAL = 532;// 0x214 No ATA_SLDR_OpenTransfer happened.
    public static final int GRC_ATA_SLDR_DATA_ERROR = 533;// 0x215 Unexpected data format received.
    public static final int GRC_ATA_SLDR_CHK_SUM_ERROR = 534;// 0x216 Checksum error in transmitted data.
    public static final int GRC_ATA_SLDR_ADDRESS_ERROR = 535;// 0x217 Address out of valid range.
    public static final int GRC_ATA_SLDR_INV_LOADFILE = 536;// 0x218 Firmware file has invalid format.
    public static final int GRC_ATA_SLDR_UNSUPPORTED = 537;// 0x219 Current (loaded) firmware doesn't support upload.
    public static final int GRC_ATA_PS_NOT_READY = 538;// 0x21A PS-System is not ready.
    public static final int GRC_ATA_ATR_SYSTEM_ERR = 539;// 0x21B ATR system error
    public static final int GRC_EDM_SYSTEM_ERR = 769;// 0x301 Fatal EDM sensor error. See for the exact reason the original EDM sensor error number. In the most cases a service problem.
    public static final int GRC_EDM_INVALID_COMMAND = 770;// 0x302 Invalid command or unknown command, see command syntax.
    public static final int GRC_EDM_BOOM_ERR = 771;// 0x303 Boomerang error.
    public static final int GRC_EDM_SIGN_LOW_ERR = 772;// 0x304 Received signal to low, prism to far away, or natural barrier, bad environment, etc.
    public static final int GRC_EDM_DIL_ERR = 773;// 0x305 obsolete
    public static final int GRC_EDM_SIGN_HIGH_ERR = 774;// 0x306 Received signal to strong, prism to near, stranger light effect.
    public static final int GRC_EDM_TIMEOUT = 775;// 0x307 Timeout, measuring time exceeded (signal too weak, beam interrupted,..)
    public static final int GRC_EDM_FLUKT_ERR = 776;// 0x308 to much turbulences or distractions
    public static final int GRC_EDM_FMOT_ERR = 777;// 0x309 filter motor defective
    public static final int GRC_EDM_DEV_NOT_INSTALLED = 778;// 0x30A Device like EGL, DL is not installed.
    public static final int GRC_EDM_NOT_FOUND = 779;// 0x30B Search result invalid. For the exact explanation, see in the description of the called function.
    public static final int GRC_EDM_ERROR_RECEIVED = 780;// 0x30C Communication ok, but an error reported from the EDM sensor.
    public static final int GRC_EDM_MISSING_SRVPWD = 781;// 0x30D No service password is set.
    public static final int GRC_EDM_INVALID_ANSWER = 782;// 0x30E Communication ok, but an unexpected answer received.
    public static final int GRC_EDM_SEND_ERR = 783;// 0x30F Data send error, sending buffer is full.
    public static final int GRC_EDM_RECEIVE_ERR = 784;// 0x310 Data receive error, like parity buffer overflow.
    public static final int GRC_EDM_INTERNAL_ERR = 785;// 0x311 Internal EDM subsystem error.
    public static final int GRC_EDM_BUSY = 786;// 0x312 Sensor is working already, abort current measuring first.
    public static final int GRC_EDM_NO_MEASACTIVITY = 787;// 0x313 No measurement activity started.
    public static final int GRC_EDM_CHKSUM_ERR = 788;// 0x314 Calculated checksum, resp. received data wrong (only in binary communication mode possible).
    public static final int GRC_EDM_INIT_OR_STOP_ERR = 789;// 0x315 During start up or shut down phase an error occured. It is saved in the DEL buffer.
    public static final int GRC_EDM_SRL_NOT_AVAILABLE = 790;// 0x316 Red laser not available on this sensor HW.
    public static final int GRC_EDM_MEAS_ABORTED = 791;// 0x317 Measurement will be aborted (will be used for the laser security)
    public static final int GRC_EDM_SLDR_TRANSFER_PENDING = 798;// 0x31E Multiple OpenTransfer calls.
    public static final int GRC_EDM_SLDR_TRANSFER_ILLEGAL = 799;// 0x31F No open transfer happened.
    public static final int GRC_EDM_SLDR_DATA_ERROR = 800;// 0x320 Unexpected data format received.
    public static final int GRC_EDM_SLDR_CHK_SUM_ERROR = 801;// 0x321 Checksum error in transmitted data.
    public static final int GRC_EDM_SLDR_ADDR_ERROR = 802;// 0x322 Address out of valid range.
    public static final int GRC_EDM_SLDR_INV_LOADFILE = 803;// 0x323 Firmware file has invalid format.
    public static final int GRC_EDM_SLDR_UNSUPPORTED = 804;// 0x324 Current (loaded) firmware doesn't support upload.
    public static final int GRC_EDM_UNKNOW_ERR = 808;// 0x328 Undocumented error from the EDM sensor, should not occur.
    public static final int GRC_EDM_DISTRANGE_ERR = 818;// 0x332 Out of distance range (dist too small or large)
    public static final int GRC_EDM_SIGNTONOISE_ERR = 819;// 0x333 Signal to noise ratio too small
    public static final int GRC_EDM_NOISEHIGH_ERR = 820;// 0x334 Noise to high
    public static final int GRC_EDM_PWD_NOTSET = 821;// 0x335 Password is not set
    public static final int GRC_EDM_ACTION_NO_MORE_VALID = 822;// 0x336 Elapsed time between prepare und start fast measurement for ATR to long
    public static final int GRC_EDM_MULTRG_ERR = 823;// 0x337 Possibly more than one target (also a sensor error)
    public static final int GRC_EDM_MISSING_EE_CONSTS = 824;// 0x338 eeprom consts are missing
    public static final int GRC_EDM_NOPRECISE = 825;// 0x339 No precise measurement possible
    public static final int GRC_EDM_MEAS_DIST_NOT_ALLOWED = 826;// 0x33A Measured distance is to big (not allowed)
    public static final int GRC_TMC_NO_FULL_CORRECTION = 1283;// 0x503 Warning: measurement without full correction
    public static final int GRC_TMC_ACCURACY_GUARANTEE = 1284;// 0x504 Info: accuracy can not be guarantee
    public static final int GRC_TMC_ANGLE_OK = 1285;// 0x505 Warning: only angle measurement valid
    public static final int GRC_TMC_ANGLE_NOT_FULL_CORR = 1288;// 0x508 Warning: only angle measurement valid but without full correction
    public static final int GRC_TMC_ANGLE_NO_ACC_GUARANTY = 1289;// 0x509 Info: only angle measurement valid but accuracy can not be guarantee
    public static final int GRC_TMC_ANGLE_ERROR = 1290;// 0x50A Error: no angle measurement
    public static final int GRC_TMC_DIST_PPM = 1291;// 0x50B Error: wrong setting of PPM or MM on EDM
    public static final int GRC_TMC_DIST_ERROR = 1292;// 0x50C Error: distance measurement not done (no aim, etc.)
    public static final int GRC_TMC_BUSY = 1293;// 0x50D Error: system is busy (no measurement done)
    public static final int GRC_TMC_SIGNAL_ERROR = 1294;// 0x50E Error: no signal on EDM (only in signal mode)
    public static final int GRC_MOT_UNREADY = 1792;// 0x700 motorization is not ready
    public static final int GRC_MOT_BUSY = 1793;// 0x701 motorization is handling another task
    public static final int GRC_MOT_NOT_OCONST = 1794;// 0x702 motorization is not in velocity mode
    public static final int GRC_MOT_NOT_CONFIG = 1795;// 0x703 motorization is in the wrong mode or busy
    public static final int GRC_MOT_NOT_POSIT = 1796;// 0x704 motorization is not in posit mode
    public static final int GRC_MOT_NOT_SERVICE = 1797;// 0x705 motorization is not in service mode
    public static final int GRC_MOT_NOT_BUSY = 1798;// 0x706 motorization is handling no task
    public static final int GRC_MOT_NOT_LOCK = 1799;// 0x707 motorization is not in tracking mode
    public static final int GRC_MOT_NOT_SPIRAL = 1800;// 0x708 motorization is not in spiral mode
    public static final int GRC_MOT_V_ENCODER = 1801;// 0x709 vertical encoder/motor error
    public static final int GRC_MOT_HZ_ENCODER = 1802;// 0x70A horizontal encoder/motor error
    public static final int GRC_MOT_HZ_V_ENCODER = 1803;// 0x70B horizontal and vertical encoder/motor error
    public static final int GRC_BMM_XFER_PENDING = 2305;// 0x901 Loading process already opened
    public static final int GRC_BMM_NO_XFER_OPEN = 2306;// 0x902 Transfer not opened
    public static final int GRC_BMM_UNKNOWN_CHARSET = 2307;// 0x903 Unknown character set
    public static final int GRC_BMM_NOT_INSTALLED = 2308;// 0x904 Display module not present
    public static final int GRC_BMM_ALREADY_EXIST = 2309;// 0x905 Character set already exists
    public static final int GRC_BMM_CANT_DELETE = 2310;// 0x906 Character set cannot be deleted
    public static final int GRC_BMM_MEM_ERROR = 2311;// 0x907 Memory cannot be allocated
    public static final int GRC_BMM_CHARSET_USED = 2312;// 0x908 Character set still used
    public static final int GRC_BMM_CHARSET_SAVED = 2313;// 0x909 Charset cannot be deleted or is protected
    public static final int GRC_BMM_INVALID_ADR = 2314;// 0x90A Attempt to copy a character block outside the allocated memory
    public static final int GRC_BMM_CANCELANDADR_ERROR = 2315;// 0x90B Error during release of allocated memory
    public static final int GRC_BMM_INVALID_SIZE = 2316;// 0x90C Number of bytes specified in header does not match the bytes read
    public static final int GRC_BMM_CANCELANDINVSIZE_ERROR = 2317;// 0x90D Allocated memory could not be released
    public static final int GRC_BMM_ALL_GROUP_OCC = 2318;// 0x90E Max. number of character sets already loaded
    public static final int GRC_BMM_CANT_DEL_LAYERS = 2319;// 0x90F Layer cannot be deleted
    public static final int GRC_BMM_UNKNOWN_LAYER = 2320;// 0x910 Required layer does not exist
    public static final int GRC_BMM_INVALID_LAYERLEN = 2321;// 0x911 Layer length exceeds maximum
    public static final int GRC_COM_ERO = 3072;// 0xC00 Initiate Extended Runtime Operation (ERO).
    public static final int GRC_COM_CANT_ENCODE = 3073;// 0xC01 Cannot encode arguments in client.
    public static final int GRC_COM_CANT_DECODE = 3074;// 0xC02 Cannot decode results in client.
    public static final int GRC_COM_CANT_SEND = 3075;// 0xC03 Hardware error while sending.
    public static final int GRC_COM_CANT_RECV = 3076;// 0xC04 Hardware error while receiving.
    public static final int GRC_COM_TIMEDOUT = 3077;// 0xC05 Request timed out.
    public static final int GRC_COM_WRONG_FORMAT = 3078;// 0xC06 Packet format error.
    public static final int GRC_COM_VER_MISMATCH = 3079;// 0xC07 Version mismatch between client and server.
    public static final int GRC_COM_CANT_DECODE_REQ = 3080;// 0xC08 Cannot decode arguments in server.
    public static final int GRC_COM_PROC_UNAVAIL = 3081;// 0xC09 Unknown RPC, procedure ID invalid.
    public static final int GRC_COM_CANT_ENCODE_REP = 3082;// 0xC0A Cannot encode results in server.
    public static final int GRC_COM_SYSTEM_ERR = 3083;// 0xC0B Unspecified generic system error.
    public static final int GRC_COM_FAILED = 3085;// 0xC0D Unspecified error.
    public static final int GRC_COM_NO_BINARY = 3086;// 0xC0E Binary protocol not available.
    public static final int GRC_COM_INTR = 3087;// 0xC0F Call interrupted.
    public static final int GRC_COM_REQUIRES_8DBITS = 3090;// 0xC12 Protocol needs 8bit encoded characters.
    public static final int GRC_COM_TR_ID_MISMATCH = 3093;// 0xC15 TRANSACTIONS ID mismatch error.
    public static final int GRC_COM_NOT_GEOCOM = 3094;// 0xC16 Protocol not recognizable.
    public static final int GRC_COM_UNKNOWN_PORT = 3095;// 0xC17 (WIN) Invalid port address.
    public static final int GRC_COM_ERO_END = 3099;// 0xC1B ERO is terminating.
    public static final int GRC_COM_OVERRUN = 3100;// 0xC1C Internal error: data buffer overflow.
    public static final int GRC_COM_SRVR_RX_CHECKSUM_ERRR = 3101;// 0xC1D Invalid checksum on server side received.
    public static final int GRC_COM_CLNT_RX_CHECKSUM_ERRR = 3102;// 0xC1E Invalid checksum on client side received.
    public static final int GRC_COM_PORT_NOT_AVAILABLE = 3103;// 0xC1F (WIN) Port not available.
    public static final int GRC_COM_PORT_NOT_OPEN = 3104;// 0xC20 (WIN) Port not opened.
    public static final int GRC_COM_NO_PARTNER = 3105;// 0xC21 (WIN) Unable to find TPS.
    public static final int GRC_COM_ERO_NOT_STARTED = 3106;// 0xC22 Extended Runtime Operation could not be started.
    public static final int GRC_COM_CONS_REQ = 3107;// 0xC23 Att to send cons reqs
    public static final int GRC_COM_SRVR_IS_SLEEPING = 3108;// 0xC24 TPS has gone to sleep. Wait and try again.
    public static final int GRC_COM_SRVR_IS_OFF = 3109;// 0xC25 TPS has shut down. Wait and try again.
    public static final int GRC_COM_NO_CHECKSUM = 3110;// 0xC26 No checksum in ASCII protocol available.
    public static final int GRC_AUT_TIMEOUT = 8704;// 0x2200 Position not reached
    public static final int GRC_AUT_DETENT_ERROR = 8705;// 0x2201 Positioning not possible due to mounted EDM
    public static final int GRC_AUT_ANGLE_ERROR = 8706;// 0x2202 Angle measurement error
    public static final int GRC_AUT_MOTOR_ERROR = 8707;// 0x2203 Motorisation error
    public static final int GRC_AUT_INCACC = 8708;// 0x2204 Position not exactly reached
    public static final int GRC_AUT_DEV_ERROR = 8709;// 0x2205 Deviation measurement error
    public static final int GRC_AUT_NO_TARGET = 8710;// 0x2206 No target detected
    public static final int GRC_AUT_MULTIPLE_TARGETS = 8711;// 0x2207 Multiple target detected
    public static final int GRC_AUT_BAD_ENVIRONMENT = 8712;// 0x2208 Bad environment conditions
    public static final int GRC_AUT_DETECTOR_ERROR = 8713;// 0x2209 Error in target acquisition
    public static final int GRC_AUT_NOT_ENABLED = 8714;// 0x220A Target acquisition not enabled
    public static final int GRC_AUT_CALACC = 8715;// 0x220B ATR-Calibration failed
    public static final int GRC_AUT_ACCURACY = 8716;// 0x220C Target position not exactly reached
    public static final int GRC_AUT_DIST_STARTED = 8717;// 0x220D Info: dist. measurement has been started
    public static final int GRC_AUT_SUPPLY_TOO_HIGH = 8718;// 0x220E external Supply voltage is too high
    public static final int GRC_AUT_SUPPLY_TOO_LOW = 8719;// 0x220F int. or ext. Supply voltage is too low
    public static final int GRC_AUT_NO_WORKING_AREA = 8720;// 0x2210 working area not set
    public static final int GRC_AUT_ARRAY_FULL = 8721;// 0x2211 power search data array is filled
    public static final int GRC_AUT_NO_DATA = 8722;// 0x2212 no data available
    public static final int GRC_KDM_NOT_AVAILABLE = 12544;// 0x3100 KDM device is not available.
    public static final int GRC_FTR_FILEACCESS = 13056;// 0x3300 File access error
    public static final int GRC_FTR_WRONGFILEBLOCKNUMBER = 13057;// 0x3301 block number was not the expected one
    public static final int GRC_FTR_NOTENOUGHSPACE = 13058;// 0x3302 not enough space on device to proceed
    public static final int GRC_FTR_INVALIDINPUT = 13059;// 0x3303 Rename of file failed.
    public static final int GRC_FTR_MISSINGSETUP = 13060;// 0x3304 invalid parameter as input
```

## 基本模块说明
**角度都是弧度值，距离都是以m为单位的Double类型**
* IAUTService

```java
    //在指定范围内搜索棱镜，返回参考GRCCode
    Response<Void> fineAdjust(double dSrchHz, double dSrchV);

    Response<AUT.SearchSpiral> getUserSpiral();

    Response<AUT.TimeOut> readTimeOut();

    Response<AUT.PosTol> readTol();

    Response<AUT.ADJMode> getFineAdjustMode();

    Response<Void> setTol(AUT.PosTol posTol);

    //转到指定位置
    Response<Void> makePositioning(double hz,double v,AUT.PosMode posMode,AUT.ATRMode atrMode);

    Response<Void> setUserSpiral(AUT.SearchSpiral searchSpiral);

    //换面
    Response<Void> changeFace(AUT.PosMode posMode, AUT.ATRMode atrMode);

    Response<Void> setFineAdjustMode(AUT.ADJMode adjMode);

    Response<Void> powerSearchNext(Long clockWise, boolean iSwing);

    Response<Void> powerSearchEnableRange(boolean enabled);

    Response<Void> powerSearchSetRange(long lMinDist, long lMaxDist);

    Response<Void> setTimeOut(AUT.TimeOut timeOut);

    Response<Void> lockIn();

    Response<Void> search(double hzArea, double vArea, boolean bDummy);

    Response<Void> powerSearchWindow();

    Response<Void> setSearchArea(AUT.SearchArea area);

```
* ITMCService
```java

    //停止所有任务
    Response<Void> stopMeasureAndMotor();
//    Response<Void> powerOn();
//
//    Response<Void> powerOff();

    Response<TMC.Station> getStation();

    Response<TMC.AtomsTemperature> getAtmCorr();

    Response<TMC.AngSwitch> getAngSwitch();

    Response<TMC.EdmMode> getEdmMode();

    Response<TMC.Height> getHeight();

    Response<Void> getInclineSwitch();

    Response<Double> getPrismCorr();

    Response<Double> getAtmPpm();

    Response<TMC.SlopDistCorr> getSlopeDistCorr();

    Response<TMC.Face> getFace();

    Response<Void> setStation(TMC.Station station);

    //设置水平角
    Response<Void> setOrientation(double hzOrientation);

    Response<Void> setHeight(double height);

    Response<Void> setAtmCorr(TMC.AtomsTemperature atomsTemperature);

    Response<Void> setAtmPpm(double ppm);


    Response<Void> setInlineSwitch(boolean onOff);

    //暂不支持
    Response<Void> setEdmMode(TMC.EdmMode mode);

    //获取距离和角度值
    Response<TMC.SimpleMeaBean> getSimpleMea(long waitTime, TMC.InclinePrg inclinePrg);

    Response<TMC.FullMeaBean> getFullMeas(long waitTime, TMC.InclinePrg inclinePrg);

    Response<TMC.Angle> getAngle1(TMC.InclinePrg mode);

    Response<TMC.HVAngle> getAngle5(TMC.InclinePrg mode);

    //启动测量必须和getXXXMea配合使用，目前command参数只支持TMC_DEF_DIST,TMC_STOP
    Response<Void> doMeasure(TMC.MeasurePrg command, TMC.InclinePrg mode);

    Response<Void> setPrismCorr(double prismConst);

    Response<Void> setAngleSwitch(TMC.AngSwitch onOff);

    Response<TMC.Coordinate> getCoordinate(long waitTime
            , TMC.InclinePrg mode);
```