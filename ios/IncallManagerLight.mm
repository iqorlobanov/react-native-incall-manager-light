#import "IncallManagerLight.h"
#import <AVFoundation/AVFoundation.h>
#import <React/RCTLog.h>
#import <UIKit/UIKit.h>

@implementation IncallManagerLight
RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(isSpeakerphoneOn)
{
    AVAudioSession *audioSession = [AVAudioSession sharedInstance];
    AVAudioSessionRouteDescription *currentRoute = audioSession.currentRoute;

    BOOL speakerphoneOn = NO;
    
    for (AVAudioSessionPortDescription *port in currentRoute.outputs) {
        if ([port.portType isEqualToString:AVAudioSessionPortBuiltInSpeaker]) {
            speakerphoneOn = YES;
            break;
        }
    }

    return @(speakerphoneOn);
}

RCT_EXPORT_METHOD(setSpeakerPhoneOn:(BOOL)isSpeakerPhoneOn)
{
    NSError *error;
    AVAudioSession *session = [AVAudioSession sharedInstance];

    // Check if the speaker is already active
    if (isSpeakerPhoneOn && ![session.currentRoute.outputs.firstObject.portType isEqualToString:AVAudioSessionPortBuiltInSpeaker]) {
        [session setCategory:AVAudioSessionCategoryPlayAndRecord
                 withOptions:AVAudioSessionCategoryOptionDefaultToSpeaker
                       error:&error];
    } else {
        [session setCategory:AVAudioSessionCategoryPlayAndRecord error:&error];
    }

    [session setActive:YES error:&error];
}

- (NSArray<NSString *> *)supportedEvents {
    return @[@"Proximity"];
}

- (void)startObserving {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(proximityStateChanged:)
                                                 name:UIDeviceProximityStateDidChangeNotification
                                               object:nil];
}

- (void)stopObserving {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)proximityStateChanged:(NSNotification *)notification {
    BOOL isNear = [[UIDevice currentDevice] proximityState];
    
    [self sendEventWithName:@"Proximity" body:@{@"isNear": @(isNear)}];
}

RCT_EXPORT_METHOD(enableProximity) {
    [[UIDevice currentDevice] setProximityMonitoringEnabled:YES];
}

RCT_EXPORT_METHOD(disableProximity) {
    [[UIDevice currentDevice] setProximityMonitoringEnabled:NO];
}


@end
