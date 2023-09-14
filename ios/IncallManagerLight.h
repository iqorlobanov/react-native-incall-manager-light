
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNIncallManagerLightSpec.h"

@interface IncallManagerLight : NSObject <NativeIncallManagerLightSpec>
#else
#import <React/RCTBridgeModule.h>

@interface IncallManagerLight : NSObject <RCTBridgeModule>
#endif

@end
