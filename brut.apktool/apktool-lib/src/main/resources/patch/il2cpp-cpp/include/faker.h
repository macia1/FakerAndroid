#include <string.h>
#include <sstream>
#include <jni.h>
using namespace std;

void onJniLoad(JavaVM *vm, void *reserved);
void fakeDex(JNIEnv *env,jobject base,const char *fakeDexAssetFileName);
void fakeApp(JNIEnv *env, jobject mContext);
void fakeCpp(void *function_address, void *replace_call,void **origin_call);
long baseIamgeAddr(char *soname);
bool installDex(JNIEnv* env, jobject mContext, string dexPath);
bool installDex(JNIEnv* env, jobject mContext, vector<string>* dexPaths);


