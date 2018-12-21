#import "RNFetchBlobFileTailer.h"

@implementation RNFetchBlobFileTailer

- (id)initWithPath:(NSString *)path refreshPeriod:(NSTimeInterval)aRefresh
{
    FILE *fh = fopen([path UTF8String], "r");
    if (!fh) {
        NSLog(@"Could not open file: %@", path);
        return nil;
    }
    return [self initWithStream:fh refreshPeriod:aRefresh];
}

- (id)initWithStream:(FILE *)fh refreshPeriod:(NSTimeInterval)aRefresh
{
    if ((self = [super init])) {
        in = fh;
        refresh = aRefresh;
    }
    return self;
}

- (void)dealloc
{
    fclose(in);
}

- (void)readIndefinitely:(void (^)(int ch))action
{
    long pos = 0L;
    
    while (1) {
        fseek(in, pos, SEEK_SET);
        int ch = fgetc(in);
        pos = ftell(in);
        if (ch != EOF) {
            action(ch);
        } else {
            [NSThread sleepForTimeInterval:refresh];
        }
    }
}

@end
