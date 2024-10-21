import { NextRequest, NextResponse } from 'next/server';

const loginUrlPath = '/login'

export function middleware(req: NextRequest) {
  const token = req.cookies.get('token')?.value;
  const url = req.nextUrl.clone();
  
  if (!token && url.pathname !== loginUrlPath) {
    url.pathname = loginUrlPath;
    return NextResponse.redirect(url);
    // return NextResponse.redirect(signInURL, {
    //     headers: {
    //       'Set-Cookie': `redirectTo=${request.url}; Path=/; HttpOnly; max-age=20;`,
    //     },
    //   })
  }


  return NextResponse.next();
}

export const config = {
  matcher: ['/((?!login|register).*)']
};