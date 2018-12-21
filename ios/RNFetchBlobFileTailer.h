#import <Foundation/Foundation.h>
#include <stdio.h>

@interface RNFetchBlobFileTailer : NSObject
{
    FILE *in;
    NSTimeInterval refresh;
}

- (id)initWithPath:(NSString *)path refreshPeriod:(NSTimeInterval)aRefresh;
- (id)initWithStream:(FILE *)fh refreshPeriod:(NSTimeInterval)aRefresh;
- (void)readIndefinitely:(void (^)(int ch))action;

@end
